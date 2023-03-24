package com.studentInformationSystem.io.data;

import java.util.ArrayList;

public interface DataAccessObject {void insert(Object object);

ArrayList<Object> listAll() ;
void update (Object object);
Object findByPrimaryKey();
void delete();
void updateByStudent();
}
