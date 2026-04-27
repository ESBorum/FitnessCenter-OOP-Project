# Reflection

## Abstract class

`Activity` is an abstract class because it represents a general activity in the fitness center.  
The system should not create a generic activity directly. Instead, it creates specific activity types such as `StrengthClass` and `CardioClass`.

This allows shared fields such as `id`, `name`, `maxParticipants`, `price` and `participants` to be placed in one superclass.

## Interface

The `Bookable` interface defines booking behavior through the methods `bookMember()` and `cancelBooking()`.

This makes the booking functionality more flexible, because classes that implement `Bookable` promise that they can handle booking and cancellation.

## Generics

`Repository<T>` is used to demonstrate generics.  
It can store different types of objects, such as `Member`, `Activity` and `Booking`, without duplicating the same list logic multiple times.

This makes the code more reusable and type-safe.

## Lambda and streams

Lambda expressions are used to sort members by name and activities by price.  
Streams are used to filter activities and show only activities with available spots.

This makes the code shorter and easier to read compared to manually writing sorting and filtering logic.

## Threads

Threads are used to simulate sending booking confirmations asynchronously.

The program creates multiple threads using `BookingConfirmationTask`, so more than one booking confirmation can be handled at the same time.

## Possible improvements

The system could be improved by adding file saving/loading, better input validation, login roles, a database, and more advanced thread safety.

For example, `bookMember()` could be made synchronized to better protect against race conditions if multiple users tried to book the same activity at the same time.