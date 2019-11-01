package com.sagar.ddd;

/**
 * Interface defines the Aggregate concept of Domain Driven Design. All aggegate Classes should implement this
 * interface. Concreate implementation class will hold a aggreate root entity and related entities or their
 * references. Aggregates should define a domain operation which should also mark a transactional boundry.
 */
public interface Aggregate {
}
