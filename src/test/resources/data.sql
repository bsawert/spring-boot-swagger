insert into breed_dto(id, name, description, akcgroup) values(1, 'Chihuahua', 'Chihuahua', 'TOY');
insert into breed_dto(id, name, description, akcgroup) values(2, 'Rat Terrier', 'Rat Terrier', 'TERRIER');
insert into breed_dto(id, name, description, akcgroup) values(3, 'Pug', 'Pug', 'TOY');
insert into breed_dto(id, name, description, akcgroup) values(4, 'Blue Tick Hound', 'Blue Tick Coonhound', 'HOUND');
insert into breed_dto(id, name, description, akcgroup) values(5, 'Mixed Breed', 'Pound Puppy', 'MISCELLANEOUS');

insert into dog_dto(id, name, description, gender) values(1, 'Puppy', 'Puppy', 'FEMALE_NEUTERED');
insert into dog_breed (fk_dog, fk_breed) values(1, 1);
insert into dog_breed (fk_dog, fk_breed) values(1, 2);

insert into dog_dto(id, name, description, gender) values(2, 'Lily', 'Lily', 'FEMALE_NEUTERED');
insert into dog_breed (fk_dog, fk_breed) values(2, 1);
insert into dog_breed (fk_dog, fk_breed) values(2, 5);

insert into dog_dto(id, name, description, gender) values(3, 'Gidget', 'Gidget', 'FEMALE_NEUTERED');
insert into dog_breed (fk_dog, fk_breed) values(3, 3);

insert into dog_dto(id, name, description, gender) values(4, 'Duesy', 'Duesenberg', 'FEMALE_NEUTERED');
insert into dog_breed (fk_dog, fk_breed) values(4, 4);
