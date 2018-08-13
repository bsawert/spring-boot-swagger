insert into breed_dto(id, name, description, akcgroup) values(1, 'Breed 1', 'Breed 1', 'MISCELLANEOUS');
insert into breed_dto(id, name, description, akcgroup) values(2, 'Breed 2', 'Breed 2', 'MISCELLANEOUS');
insert into breed_dto(id, name, description, akcgroup) values(3, 'Breed 3', 'Breed 3', 'HOUND');

insert into dog_dto(id, name, description, gender) values(1, 'Dog 1', 'Dog 1', 'NEUTERED');
insert into dog_breed (fk_dog, fk_breed) values(1, 1);
insert into dog_breed (fk_dog, fk_breed) values(1, 2);

insert into dog_dto(id, name, description, gender) values(2, 'Dog 2', 'Dog 2', 'UNKNOWN');
insert into dog_breed (fk_dog, fk_breed) values(2, 3);
