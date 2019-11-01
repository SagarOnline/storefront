package com.sagar.ddd;

/**
 * This interface represents a Command in CQRS pattern. Concreate class should represents the
 * parameters required for invoking Command type operation on  Domain model.
 *
 * Application layer (which will be on top of Domain layer) is responsible for registering and implementing a Handler
 * for respective Command. Handler will use domain objects to execute required operation (invoke operations on service
 * or aggregates)
 */
public interface Command {
}
