# Bitemporal chaining

## Introduction

This article introduces the concept of bitemporal chaining for relational databases. Using an example of a bank account, it shows how bitemporal chaining database is represented in the database, it is easy to make corrections to historical data without losing any history.

## Bugs-R-Us Bank

Consider the case of the Bugs-R-Us bank. The bank has buggy ATMs!

These ATMs are full of software bugs that result in ATM activity not being correctly posted to your account. Sometimes the bank says you have less money than you actually have;  sometimes it says you have more money than you actually have!

All of this is very frustrating, but the bank happily adjusts your balance everytime you report a discrepancy.

However, in addition to the ATMs being buggy, the bank has a new problem. The account balances have been manually adjusted so many timea,
that they they are unable to resolve an account's history.

Lucikly, they have learned about bitemporal chaining, thus allowing them to fix these problems.

## Bitemporal chaining

In [bitemporal chaining](https://en.wikipedia.org/wiki/Bitemporal_Modeling), all changes to a database are tracked along two dimensions:
* *Processing Time* - This is when the change actually occurred in the real world
* *Transaction Time* - This is when the change was recorded in the database

While these two times are often the same, they can be different. Consider the following example:

## A few days in the life of a Bugs-R-Us customer

### Day 1 (Jan 1) - Open an account 

On *Jan 1*, you open a new bank account with a balance of *$100*. The bank updates its database (table) with an entry for your account.

Since bitemporal chaining is being used, each row in the table has four timestamp columns:
* *FROM_Z* and *THRU_Z* track the validity of the row along the processing time dimension
* *IN_Z* and *OUT_Z* track the validity of the row along the transaction time dimension

The table looks as follows:

| Account # | Balance | FROM_Z | THRU_Z |  IN_Z |  OUT_Z |  Row Number |
| --- | --- | --- | --- | --- | --- | --- |
| ACC1      | 100      | Jan 1 | Infinity | Jan 1 | Infinity | 1 |

> For simplicity, this example will use dates (formatted as 'Jan 1') instead of timestamps

> The *Row Number* column provides an easy way to refer to rows in this document. It is not part of the table schema.

> *Infinity* is a magic timestamp chosen such that it cannot possibly be a valid date in the system e.g. *9999/1/1*.

Row 1 records the following facts 
---------------------------------
* The account was created on *Jan 1* (today), therefore `FROM_Z = Jan 1`
* The acccount was added to the database on *Jan 1* (today), therefore `IN_Z = Jan 1`
* This is the only row for this account. And we mark these rows as valid by setting *THRU_Z* and *OUT_Z* equal to *Infinity*.


### Day 2 (Jan 2) - Deposit $200

The next day, on *Jan 2*, you deposit *$200* at one of the ATMs.

The goal of bitemporal milestoning is to track changes along both dimensions, so the bank cannot simply update the balance in *Row 1*. They cannot delete *Row 1* and insert a new row either as that loses history.

In general, making changes to a bitemporally chained database is a two step process:
* Invalidate rows whose view of the world is incorrect
* Add new rows to reflect the new view of the world

####  **Invalidating rows**

*Row 1* currently states that the balance is *$0* from *Jan 1* to *Infinity*. This is not true anymore as the bank just accepted a *$200* deposit on *Jan 2*, so we invalidate *Row 1* by setting its *OUT_Z* to *Jan 2* (today).

| Account # | Balance | FROM_Z | THRU_Z |  IN_Z |  OUT_Z |  Row Number |
| --- | --- | --- | --- | --- | --- | --- |
| ACC1      | 100      | Jan 1 | Infinity | Jan 1 | Jan 2 | 1 |

#### **Adding new rows**

Our new view of the world is as follows :
* From *Jan 1* to *Jan 2*, *balance = $100* (opening balance)
* From *Jan 2* to *Infinity*, *balance = $300* (opening balance plus deposit of *$200*)

So we add *Row 2* and *Row 3* to capture these facts. The *IN_Z* and *OUT_Z* of these rows captures the fact that these
changes were made today and that these rows represent the latest state of the account (i.e *OUT_Z = Infinity*).

| Account # | Balance | FROM_Z | THRU_Z |  IN_Z |  OUT_Z |  Row Number |
| --- | --- | --- | --- | --- | --- | --- |
| ACC1      | 100      | Jan 1 | Infinity | Jan 1 | Jan 2 | 1 |
| ACC1      | 100      | Jan 1 | Jan 2 | Jan 2 | Infinity | 2 |
| ACC1      | 300      | Jan 2 | Infinity | Jan 2 | Infinity | 3 |

### Ten days later, on *Jan 12*, you deposit *$50*.

> But remember, the ATM is buggy!

Due to a software bug, the ATM does not send your deposit to the bank. While you walk away thinking your account contains $350, your account truly contains $300.

| Account # | Balance | FROM_Z | THRU_Z |  IN_Z |  OUT_Z |  Row Number |
| --- | --- | --- | --- | --- | --- | --- |
| ACC1      | 100      | Jan 1 | Infinity | Jan 1 | Jan 2 | 1 |
| ACC1      | 100      | Jan 1 | Jan 2 | Jan 2 | Infinity | 2 |
| ACC1      | 300      | Jan 2 | Infinity | Jan 2 | Infinity | 3 |

### Five days later, on *Jan 17* - You are mad!

Five days later, on *Jan 17*, you check your bank account online and realize the mistake. Your account is short by *$50*. Furious, you call the bank to complain. They are very apologetic and agree to adjust your balance.

Just as before, the bank wants to preserve history in both dimensions. They follow the same approach:
* Invalidate rows whose view of the world is incorrect
* Add new rows to reflect the new view of the world

## Invalidate rows

*Row 1* is already invalid, therefore it does not need to be updated.

*Row 2* and *Row 3* are invalid along the processing time dimension, however, we want to preserve the fact that they are invalid. We invalidate them by setting their *OUT_Z* to *Jan 17* (today).

| Account # | Balance | FROM_Z | THRU_Z |  IN_Z |  OUT_Z |  Row Number |
| --- | --- | --- | --- | --- | --- | --- |
| ACC1      | 100      | Jan 1 | Infinity | Jan 1 | Jan 2 | 1 |
| ACC1      | 100      | Jan 1 | Jan 2 | Jan 2 | Jan 17 | 2 |
| ACC1      | 300      | Jan 2 | Infinity | Jan 2 | Jan 17 | 3 |

## Add new rows

Our new view of the world is as follows :
* From *Jan 1* to *Jan 2*, *balance = $100* (opening balance)
* From *Jan 2* to *Jan 12*, *balance = $300* (opening balance + deposit of $200 on Jan 2)
* From *Jan 12* to *Infinity*, *balance = $350* (opening balance + deposit of $200 on Jan 2 + deposit of $50 on Jan 12)

Since we are adding these rows on *Jan 17* (today), the *IN_Z* of these newly added rows is *Jan 17*.

| Account # | Balance | FROM_Z | THRU_Z |  IN_Z |  OUT_Z |  Row Number |
| --- | --- | --- | --- | --- | --- | --- |
| ACC1      | 100      | Jan 1 | Infinity | Jan 1 | Jan 2 | 1 |
| ACC1      | 100      | Jan 1 | Jan 2 | Jan 2 | Jan 17 | 2 |
| ACC1      | 300      | Jan 2 | Infinity | Jan 2 | Jan 17 | 3 |
| ACC1      | 100      | Jan 1 | Jan 2 | Jan 17 | Infinity | 4 |
| ACC1      | 300      | Jan 2 | Jan 12 | Jan 17 | Infinity | 5 |
| ACC1      | 350      | Jan 12 | Infinity | Jan 17| Infinity | 6 | 

## Visualizing bitemporal chaining

It is very helpful to visualize the history of changes in a two dimensional chart. The following figure contains three charts showing how the table changes over time.

Each colored (and numbered) rectangle corresponds to a row in the database.
Even though the graphs are not drawn to scale, it is clear that each change does not destory history (i.e each change only introduces more rectangles).

![Visualization](/docs/visualization.png?raw=true "Visualization")
