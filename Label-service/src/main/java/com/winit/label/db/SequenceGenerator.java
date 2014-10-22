package com.winit.label.db;

public interface SequenceGenerator
{
	String nextVal(String seqKey);
	String currentVal(String seqKey);
}
