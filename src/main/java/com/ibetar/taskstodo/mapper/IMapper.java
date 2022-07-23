package com.ibetar.taskstodo.mapper;

public interface IMapper <I, O>{
    public O map(I in);
    //I = INPUT DATA ENTER
    //O = OUTPUT DATA
}
