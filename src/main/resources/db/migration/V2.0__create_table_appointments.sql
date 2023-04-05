CREATE TABLE appointments (

    id varchar(36) not null,
    patient_id varchar(36),
    doctor_id varchar(36),
    date timestamp not null,

    primary key(id),
    foreign key (patient_id) references patients(id),
    foreign key (doctor_id) references doctors(id)

)