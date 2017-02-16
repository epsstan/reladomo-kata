# BugsRus bank

Consider the case of the BugsRus bank. The bank has buggy ATMs. These ATMs are full of software bugs 
that result in all ATM activity not being correctly posted to your account. Sometimes the bank says you have
less money than you actually have ; sometimes it says you have more money than you actually have!

All of this is very frustrating. But the bank is more than happy to manually adjust your bank balance everytime you report a discrepancy. 

However, the bank has a new problem (on top of the ATMs being buggy). They have been manually adjusting 
the balance so many times, that they are completely unable to reason about your account's history. In particular, they have no way of reconciling your view of your bank account against their view of the bank 
account at a given point in time. They also do not have a way to track when your bank balance was manually adjusted.

Lucikly, they have learnt about bitemporal chaining that will help them reason about and track changes 
to your bank account. (Now all they have to do is fix the buggy ATMs).

# Bitemporal chaining

In bitemporal chaining, all changes to a database are tracked along two dimensions :
* Valid Time - This is when the change actually occurred in the world (i.e wall clock time)
* Transaction Time - This is when the change actually was recorded in the database

While these two times are often the same, they can be different. And when they are different, bitemporal chaining helps to reason about and track these changes.

## A few days  in the life of a BugsRus customer

## Day 1 - Open an account 

Imagine you have just opened your bank account with a balance of $0. The bank updates it's database (table) with an entry for your account.

Since bitemporal chaining is being used, each row in the table has four timestamp columns :
* FROM_Z and THRU_Z track the validity of the row along the processing time dimension
* IN_Z and OUT_Z track the validity of the row along the transaction time dimension

The table looks as follows. (The 'Row Number' column is included just to make it easy to refer to rows in this document. It is not actually part of the table schema.)

| Account # | Balance | FROM_Z        | THRU_Z        | IN_Z          | OUT_Z         | Row Number |
| --        | --      | --            | --            | --            | --            | --         |
| ACC1      | 0       | 2017/1/1 9 AM | 9999/1/1 9 AM | 2017/1/1 9 AM | 9999/1/1 9 AM | 1          |

* The Account # and Balance columns are straightforward
* We want to track the fact that the account was opened today (processing time). So FROM_Z = 2017/1/1 9 AM
* This fact is true for the foreseeable future (i.e till new transactions are posted). So THRU_Z = 9999/1/1 9 AM. Here 9999/1/1 is used as a magic timestamp to indicate infinity.
* The acccount was added to the database today (transaction time). So IN_Z = 2017/1/1 9 AM as well.
* Since this change is valid for the foreseeable future, OUT_Z = Infinity.

## Day 2 - Deposit $100

The next day, you deposit $100 at one of the ATMs. But the ATM is buggy !! Instead of updating your balance
with $100, it updates it with $10 !!

Since the goal of bitemporal milestoning is to track changes along both dimensions, we cannot simply update the balance in Row 1. 

We start by adding Row 2 which records two facts  
* From 2017/1/1 to 2017/1/2 the account had a balance of $0. So FROM_Z = 2017/1/1, THRU_Z=2017/1/2
* The record was added to the database on 2017/1/2. So IN_Z = 2017/1/2
* Since this change is valid for the forseeable future, OUT_Z = Infinity

We then add Row 3 which records two facts as well
* Sarting from 2017/1/2 the account has a new balance of $10. So FROM_Z = 2017/1/2
* This fact is true for the foreseaable future. So THRU_Z = Infinity
* The record was added to the database on 2017/1/2. So IN_Z = 2017/1/2
* Since this change is valid for the forseeable future, OUT_Z = Infinity

Now, Row 1 contradicts Row 2. Row 1 states the balance was $0 from 2017/1/1 to Infinity, whereas Row 2 states the balance was $10 from 2017/1/1 to 2017/1/2. So we fix Row 1 to indicate that it is no longer valid by updating it's OUT_Z to the current timestamp of 2017/1/2.







