insert into user (id, active, email, firstname, lastname, password, mobile, houseno, street, city, pincode, state, country) values (1, 1, 'admin@anyemail.com', 'Admin', ' ', '$2a$10$BAuZTJxHyGvfNrsornpuZurnhfDGx7Dy0gspSSRxg2MOyUXIf.89S','123','4125','park street','perth','1000','westaus','Australia');
insert into user (id, active, email, firstname, lastname, password, mobile, houseno, street, city, pincode, state, country) values (2, 1, 'user@wipro.com', 'Online', 'User', '$2a$10$IT8w4fVbJTGGeRSUbzatPOhm6w3G4NlOy52XfFvKv/3CD.RGE802.','4562','4745','king street','sydney','2000','eastaus','Australia');
insert into user (id, active, email, firstname, lastname, password, mobile, houseno, street, city, pincode, state, country) values (3, 1, 'sr@wipro.com', 'Online1', 'User1', '$2a$10$IT8w4fVbJTGGeRSUbzatPOhm6w3G4NlOy52XfFvKv/3CD.RGE802.','526','741','hinjewadi','pune','2000','mah','India');
insert into role(name) values ('ROLE_USER');
insert into role(name) values ('ROLE_ADMIN');
insert into users_roles (user_id, role_id) values (1, 2);
insert into users_roles (user_id, role_id) values (2, 1);
insert into assessment_data(user_name, question_number, selected_option) values('user@wipro.com',1,'18to40');
insert into assessment_data(user_name, question_number, selected_option) values('user@wipro.com',2,'5to10');
insert into assessment_data(user_name, question_number, selected_option) values('user@wipro.com',3,'3to5');
insert into assessment_data(user_name, question_number, selected_option) values('user@wipro.com',4,'2to5');
insert into assessment_data(user_name, question_number, selected_option) values('user@wipro.com',5,'Most concerned aboout my investment gaining value');
insert into assessment_data(user_name, question_number, selected_option) values('user@wipro.com',6,'DoNothing');

insert into assessment_data(user_name, question_number, selected_option) values('sr@wipro.com',1,'18to40');
insert into assessment_data(user_name, question_number, selected_option) values('sr@wipro.com',2,'10to30');
insert into assessment_data(user_name, question_number, selected_option) values('sr@wipro.com',3,'lessthan3years');
insert into assessment_data(user_name, question_number, selected_option) values('sr@wipro.com',4,'2to5');
insert into assessment_data(user_name, question_number, selected_option) values('sr@wipro.com',5,'Most concerned aboout my investment gaining value');
insert into assessment_data(user_name, question_number, selected_option) values('sr@wipro.com',6,'SellSomeShares');


create table question_table (
id int not null, 
question varchar(255) not null, 
select_options varchar(255) not null, 
score int not null,
primary key (id, question, select_options));

-- Fist question

insert into question_table(id, question, select_options, score)
values (1, 'My Age (in years) is', 'lessthan18', 10 );
insert into question_table(id, question, select_options, score)
values (1, 'My Age (in years) is', '18to40', 8 );
insert into question_table(id, question, select_options, score)
values (1, 'My Age (in years) is', '40to50', 6 );
insert into question_table(id, question, select_options, score)
values (1, 'My Age (in years) is', '50to60', 4 );
insert into question_table(id, question, select_options, score)
values (1, 'My Age (in years) is', 'greaterthan60', 2 );

-- Second question
insert into question_table(id, question, select_options, score)
values (2, 'My annual income in INR is', 'lessthan1Lakh', 2 );
insert into question_table(id, question, select_options, score)
values (2, 'My annual income in INR is', '1to5', 4);
insert into question_table(id, question, select_options, score)
values (2, 'My annual income in INR is', '5to10', 6 );
insert into question_table(id, question, select_options, score)
values (2, 'My annual income in INR is', '10to30', 8);
insert into question_table(id, question, select_options, score)
values (2, 'My annual income in INR is', 'greaterthan30', 10 );

-- Third question
insert into question_table(id, question, select_options, score)
values (3, 'I plan to begin widhdrawring money from my investments in', 'lessthan3years', 3 );
insert into question_table(id, question, select_options, score)
values (3, 'I plan to begin widhdrawring money from my investments in', '3to5', 6 );
insert into question_table(id, question, select_options, score)
values (3, 'I plan to begin widhdrawring money from my investments in', '6to10', 8 );
insert into question_table(id, question, select_options, score)
values (3, 'I plan to begin widhdrawring money from my investments in', 'morethan11', 10 );

-- Fourth question

insert into question_table(id, question, select_options, score)
values (4, 'Once i begin widhtrawing funds from my investments, I plan to spend all the funds in', 'lessthan2years', 3 );
insert into question_table(id, question, select_options, score)
values (4, 'Once i begin widhtrawing funds from my investments, I plan to spend all the funds in', '2to5', 6 );
insert into question_table(id, question, select_options, score)
values (4, 'Once i begin widhtrawing funds from my investments, I plan to spend all the funds in', '6to10', 8);
insert into question_table(id, question, select_options, score)
values (4, 'Once i begin widhtrawing funds from my investments, I plan to spend all the funds in', 'morethan11', 10);

-- Fifth question

insert into question_table(id, question, select_options, score)
values (5, 'When I invest my money, I am', 'Most concerned aboout my investment losing value', 3 );
insert into question_table(id, question, select_options, score)
values (5, 'When I invest my money, I am', 'Equally concerned about my investment losing or gaining value', 7 );
insert into question_table(id, question, select_options, score)
values (5, 'When I invest my money, I am', 'Most concerned aboout my investment gaining value', 10);

-- Sixth question
insert into question_table(id, question, select_options, score)
values (6, 'Imagine that in past 3 months overall stock market lost 25% of its value. An individual stock you has own also lost 25% of its value. What would you do?', 'SellShares', 3 );
insert into question_table(id, question, select_options, score)
values (6, 'Imagine that in past 3 months overall stock market lost 25% of its value. An individual stock you has own also lost 25% of its value. What would you do?', 'SellSomeShares', 6 );
insert into question_table(id, question, select_options, score)
values (6, 'Imagine that in past 3 months overall stock market lost 25% of its value. An individual stock you has own also lost 25% of its value. What would you do?', 'DoNothing', 8);
insert into question_table(id, question, select_options, score)
values (6, 'Imagine that in past 3 months overall stock market lost 25% of its value. An individual stock you has own also lost 25% of its value. What would you do?', 'BuyMoreShares', 10 );

-- create table investment recommendations

 create table investment_recommendations (id int primary key, risk_profile_score varchar(20), 
 risk_profile_name varchar(50), investment_strategy_description varchar(150), dept_recommendation int, equity_recommendations int);

 insert into investment_recommendations(id, risk_profile_score,risk_profile_name, investment_strategy_description, dept_recommendation, equity_recommendations )
 values(1, '16-22', 'Conservative', 'Investment strategy should be conservative, with entire amount invested in debt fund', 100, 0);
 
 insert into investment_recommendations(id, risk_profile_score,risk_profile_name, investment_strategy_description, dept_recommendation, equity_recommendations )
 values(2, '23-32', 'Moderately Conservative', 'Investment strategy should be moderately conservative, with 80% invested in debt fund and 20% in equity funds', 80, 20);
 
 insert into investment_recommendations(id, risk_profile_score,risk_profile_name, investment_strategy_description, dept_recommendation, equity_recommendations )
 values(3, '33-42', 'Moderate', 'Investment strategy should be moderate, with 50% invested in debt fund and 50% in equity funds', 50, 50);
 
 insert into investment_recommendations(id, risk_profile_score,risk_profile_name, investment_strategy_description, dept_recommendation, equity_recommendations )
 values(4, '43-52', 'Moderately Aggresive', 'Investment strategy should be moderately aggresive, with 25% invested in debt fund and 80% in equity funds', 20, 80);
 
 insert into investment_recommendations(id, risk_profile_score,risk_profile_name, investment_strategy_description, dept_recommendation, equity_recommendations )
 values(5, '53-60', 'Aggresive', 'Investment strategy should be aggresive, with entire amount invested in equity fund', 0, 100);

