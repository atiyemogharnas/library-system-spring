package com.library.system.patterns.observer;

public interface EventHandler {
     void add(Observer observer) ;
     void execute();
}
