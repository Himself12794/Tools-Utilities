package com.pwhiting.collect;

import java.util.Collection;

import com.google.common.base.Predicate;

public interface LimitedCollection<E> extends Collection<E> {

	void setFilter(Predicate<? super E> filter);
	
	void removeLimit();
	
	boolean isLimited();
	
}
