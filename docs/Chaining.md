# BugsRus bank

Consider the case of the BugsRus bank. The bank has buggy ATMs. These ATMs are full of software bugs 
that result in all ATM activity not being correctly posted to your account. Sometimes the bank says you have
less money than you actually have ; sometimes it says you have more money than you actually have!

All of this is very frustrating. But the bank happily adjusts your balance everytime you report a discrepancy. 

However, the bank has a new problem (on top of the ATMs being buggy). They have been manually adjusting 
the balance so many times, that they are completely unable to reason about your account's history. In particular, they have no way of reconciling your view of your bank account against their view of the bank 
account at a given point in time. They also do not have a way to track when your bank balance was manually adjusted.

Lucikly, they have learnt about bitemporal chaining that will help them fix these problems. 

# Bitemporal chaining

In bitemporal chaining, all changes to a database are tracked along two dimensions :
* Processing Time - This is when the change actually occurred in the world 
* Transaction Time - This is when the change actually was recorded in the database

While these two times are often the same, they can be different. Consider the following example.

## A few days in the life of a BugsRus customer

## Day 1 - Open an account 

On 2017/1/1 you open a new bank account with a balance of $0. The bank updates it's database (table) with an entry for your account.

Since bitemporal chaining is being used, each row in the table has four timestamp columns :
* FROM_Z and THRU_Z track the validity of the row along the processing time dimension
* IN_Z and OUT_Z track the validity of the row along the transaction time dimension


The table looks as follows. 

| Account # | Balance | FROM_Z | THRU_Z |  IN_Z |  OUT_Z |  Row Number |
| --- | --- | --- | --- | --- | --- | --- |
| ACC1      | 0       | 2017/1/1 | 9999/1/1 | 2017/1/1 | 9999/1/1 | 1 |

> For simplicity, this example will use dates instead of timestamps

> The 'Row Number' column provides an easy way to refer to rows in this document. It is not part of the table schema.

> 9999/1/1 is used as a magic timestamp to indicate infinity

Row 1 records the following facts 
* The account was created on today (2017/1/1). This fact is true for the forseeable future. So FROM_Z = 2017/1/1, THRU_Z = Infinity
* The acccount was added to the database today (2017/1/1). This change is valid for the foreseeable future. So IN_Z = 2017/1/1, OUT_Z = Infinity

## Day 2 - Deposit $100

The next day, on 2017/1/2 you deposit $100 at one of the ATMs.

> But the ATM is buggy! Instead of updating your balance with $100, it updates it with just $10!!

The goal of bitemporal milestoning is to track changes along both dimensions. So we cannot simply update the balance in Row 1. 

We start by adding Row 2 which records two facts  
* From yesterday (2017/1/1) to today (2017/1/2) the account had a balance of $0. So FROM_Z = 2017/1/1, THRU_Z=2017/1/2
* Row 2 was added to the database today (2017/1/2). This change is valid for the foreseeable future. So IN_Z = 2017/1/2, OUT_Z = Infinity

| Account # | Balance | FROM_Z | THRU_Z |  IN_Z |  OUT_Z |  Row Number |
| --- | --- | --- | --- | --- | --- | --- |
| ACC1      | 0       | 2017/1/1 | 9999/1/1 | 2017/1/1 | 9999/1/1 | 1 |
| ACC1      | 0       | 2017/1/1 | 2017/1/2 | 2017/1/2 | 9999/1/1 | 2 |

We then add Row 3 which records two facts as well
* Starting today (2017/1/2) the account has a balance of $10. This is true for the foreseeable future. So FROM_Z = 2017/1/2, THRU_Z = Infinity
* Row 3 was added to the database today (2017/1/2). This change is valid for the forseeable future. So IN_Z = 2017/1/2, OUT_Z = Infinity

| Account # | Balance | FROM_Z | THRU_Z |  IN_Z |  OUT_Z |  Row Number |
| --- | --- | --- | --- | --- | --- | --- |
| ACC1      | 0       | 2017/1/1 | 9999/1/1 | 2017/1/1 | 9999/1/1 | 1 |
| ACC1      | 0       | 2017/1/1 | 2017/1/2 | 2017/1/2 | 9999/1/1 | 2 |
| ACC1      | 10      | 2017/1/2 | 9999/1/2 | 2017/1/2 | 9999/1/1 | 3 |

Now, Row 1 contradicts Row 2. Row 1 states the balance was $0 from 2017/1/1 to Infinity, whereas Row 2 states the balance was $10 from 2017/1/1 to 2017/1/2. So we fix Row 1 to indicate that it is no longer valid by updating it's OUT_Z to the current timestamp of 2017/1/2.

| Account # | Balance | FROM_Z | THRU_Z |  IN_Z |  OUT_Z |  Row Number |
| --- | --- | --- | --- | --- | --- | --- |
| ACC1      | 0       | 2017/1/1 | 9999/1/1 | 2017/1/1 | 2017/1/2 | 1 |
| ACC1      | 0       | 2017/1/1 | 2017/1/2 | 2017/1/2 | 9999/1/1 | 2 |
| ACC1      | 10      | 2017/1/2 | 9999/1/2 | 2017/1/2 | 9999/1/1 | 3 |







