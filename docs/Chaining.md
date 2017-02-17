# Bitemporal chaining

## Introduction

This article introduces the concept of bitemporal chaining for relational databases. Using an example of a bank account, it shows how bitemporal chaining database, it is easy to make corrections to historical data without losing any history.

## Bugs-R-Us Bank

Consider the case of the Bugs-R-Us bank. The bank has buggy ATMs!!

These ATMs are full of software bugs that result in ATM activity not being correctly posted to your account. Sometimes the bank says you have less money than you actually have ; sometimes it says you have more money than you actually have!

All of this is very frustrating. But the bank happily adjusts your balance everytime you report a discrepancy. 

However, the bank has a new problem (on top of the ATMs being buggy). They have been manually adjusting 
the balance so many times, that they are completely unable to reason about your account's history. 

Lucikly, they have learnt about bitemporal chaining that will help them fix these problems. 

## Bitemporal chaining

In bitemporal chaining, all changes to a database are tracked along two dimensions :
* Processing Time - This is when the change actually occurred in the world 
* Transaction Time - This is when the change actually was recorded in the database

While these two times are often the same, they can be different. Consider the following example.

## A few days in the life of a Bugs-R-Us customer

### Day 1 (2017/1/1) - Open an account 

On 2017/1/1 you open a new bank account with a balance of $100. The bank updates it's database (table) with an entry for your account.

Since bitemporal chaining is being used, each row in the table has four timestamp columns :
* FROM_Z and THRU_Z track the validity of the row along the processing time dimension
* IN_Z and OUT_Z track the validity of the row along the transaction time dimension

The table looks as follows. 

| Account # | Balance | FROM_Z | THRU_Z |  IN_Z |  OUT_Z |  Row Number |
| --- | --- | --- | --- | --- | --- | --- |
| ACC1      | 100      | 2017/1/1 | 9999/1/1 | 2017/1/1 | 9999/1/1 | 1 |

> For simplicity, this example will use dates instead of timestamps

> The 'Row Number' column provides an easy way to refer to rows in this document. It is not part of the table schema.

> 9999/1/1 is used as a magic timestamp to indicate infinity

Row 1 records the following facts 
* The account was created on today (2017/1/1). This fact is true for the forseeable future. So FROM_Z = 2017/1/1, THRU_Z = Infinity
* The acccount was added to the database today (2017/1/1). This change is valid for the foreseeable future. So IN_Z = 2017/1/1, OUT_Z = Infinity

### Day 2 (2017/1/2) - Deposit $200

The next day, on 2017/1/2 you deposit $200 at one of the ATMs.

The goal of bitemporal milestoning is to track changes along both dimensions. So the bank cannot simply update the balance in Row 1. They cannot delete Row 1 and insert a new row either as that loses history.

In general, making changes to a bitemporally chained database is a two step process :
* Invalidate rows whose view of the world is incorrect
* Add new rows to reflect the new view of the world

**Invalidating rows **

Row 1 currently states that the balance is $0 from 2017/1/1 to Infinity. This is not true anymore as the bank just accepted a $200 deposit on 2017/1/2. So we invalidate Row 1 by setting its OUT_Z to today (2017/1/2). 

| Account # | Balance | FROM_Z | THRU_Z |  IN_Z |  OUT_Z |  Row Number |
| --- | --- | --- | --- | --- | --- | --- |
| ACC1      | 100      | 2017/1/1 | 9999/1/1 | 2017/1/1 | 2017/1/2 | 1 |

**Adding new rows **

Our new view of the world is as follows :
* From 2017/1/1 to 2017/1/2, balance = $100 (opening balance)
* From 2017/1/2 to Infinity, balance = $300 (opening balance plus deposit of $200)

So we add Rows 2 and 3 to capture these facts. The IN_Z and OUT_Z of these rows captures the fact that these
chages were made today and that these rows represent the latest state of the account (i.e OUT_Z is Infinity)

| Account # | Balance | FROM_Z | THRU_Z |  IN_Z |  OUT_Z |  Row Number |
| --- | --- | --- | --- | --- | --- | --- |
| ACC1      | 100      | 2017/1/1 | 9999/1/1 | 2017/1/1 | 2017/1/2 | 1 |
| ACC1      | 100      | 2017/1/1 | 2017/1/2 | 2017/1/2 | 9999/1/1 | 2 |
| ACC1      | 300      | 2017/1/2 | 9999/1/1 | 2017/1/2 | 9999/1/1 | 3 |

### Ten days later (2017/1/12) - Deposit $50

Ten days later on 2017/1/12 you deposit $50.

> But remember, the ATM is buggy!

Because of a software bug the ATM does not send your deposit to the bank. While you walk away thinking your account has $350, your account actually has only $300.

| Account # | Balance | FROM_Z | THRU_Z |  IN_Z |  OUT_Z |  Row Number |
| --- | --- | --- | --- | --- | --- | --- |
| ACC1      | 100      | 2017/1/1 | 9999/1/1 | 2017/1/1 | 2017/1/2 | 1 |
| ACC1      | 100      | 2017/1/1 | 2017/1/2 | 2017/1/2 | 9999/1/1 | 2 |
| ACC1      | 300      | 2017/1/2 | 9999/1/1 | 2017/1/2 | 9999/1/1 | 3 |

### Another five days later (2017/1/17) - You are mad !!

Five days later on 2017/1/17, you check your bank account online and realize the mistake. Your account is short by $50. Furious, you call the bank to complain. They are vey apologetic and agree to adjust your balance.

Just as before, the bank wants to preserve history in both dimensions. They follow the same approach :
* Invalidate rows whose view of the world is incorrect
* Add new rows to reflect the new view of the world

**Invalidate rows** 

Row 1 is already invalid. It does not need to be updated.

Rows 2 and 3 are invalid along the processing time dimension. However, we want to preserve the fact that they are invalid. So we invalidate them by setting their OUT_Z to today (2017/1/17).

| Account # | Balance | FROM_Z | THRU_Z |  IN_Z |  OUT_Z |  Row Number |
| --- | --- | --- | --- | --- | --- | --- |
| ACC1      | 100      | 2017/1/1 | 9999/1/1 | 2017/1/1 | 2017/1/2 | 1 |
| ACC1      | 100      | 2017/1/1 | 2017/1/2 | 2017/1/2 | 2017/1/17 | 2 |
| ACC1      | 300      | 2017/1/2 | 9999/1/1 | 2017/1/2 | 2017/1/17 | 3 |

**Add new rows**

Our new view of the world is as follows :
* From 2017/1/1 to 2017/1/2, balance = $100 (opening balance)
* From 2017/1/2 to 2017/1/12, balance = $300 (opening balance + deposit of $200 on 2017/1/2)
* From 2017/1/12 to Infinity, balance = $350 (opening balance + deposit of $200 on 2017/1/2 + deposit of $50 on 2017/1/12)

Since we are adding these rows today (2017/1/17), the IN_Z of these newly added rows = 2017/1/17.

| Account # | Balance | FROM_Z | THRU_Z |  IN_Z |  OUT_Z |  Row Number |
| --- | --- | --- | --- | --- | --- | --- |
| ACC1      | 100      | 2017/1/1 | 9999/1/1 | 2017/1/1 | 2017/1/2 | 1 |
| ACC1      | 100      | 2017/1/1 | 2017/1/2 | 2017/1/2 | 2017/1/17 | 2 |
| ACC1      | 300      | 2017/1/2 | 9999/1/1 | 2017/1/2 | 2017/1/17 | 3 |
| ACC1      | 100      | 2017/1/1 | 2017/1/2 | 2017/1/17 | 9999/1/1 | 4 |
| ACC1      | 300      | 2017/1/2 | 2017/1/12 | 2017/1/17 | 9999/1/1 | 5 |
| ACC1      | 350      | 2017/1/12 | 9999/1/1 | 2017/1/17| 9999/1/1 | 6 | 
