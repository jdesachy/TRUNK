create table PICTURES_ACTIVITIES (
ID_ACTIVITY INTEGER,
URL VARCHAR(100),
PRIMARY KEY(ID_ACTIVITY, URL),
FOREIGN KEY (ID_ACTIVITY) REFERENCES ACTIVITIES(ID)
)