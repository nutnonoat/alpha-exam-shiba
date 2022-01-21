# Create REST API with JAVA Spring Boot Practice
## Information
### Exam agreement
- Before begin exam process turn camera around to show surrounding
- Use only one monitor
- Always turn on video and microphone
- Make sure you have stable internet
- Please be in private area that no one else will walk in (or make some noise) during test
- Searching in Google is allowed. But we will always take note on what you searched.
- Sharing the exam to other people is prohibited

### Pre-requisites
- IntelliJ or any code editor https://www.jetbrains.com/idea/download/ 
- Java 8 https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html
- Gradle https://gradle.org/install/ 
- Lombok https://projectlombok.org/download
- (MAC) Docker https://hub.docker.com/editions/community/docker-ce-desktop-mac
- (Windows) Docker https://docs.docker.com/desktop/windows/install/
- Postman https://www.postman.com/downloads/ 
- Github account https://github.com/

### Starting
Create Database using docker-compose that we provided

### Database
We already set connection to Database
- Database: MSSQL
- Account: SA
- Password: yourStrong(!)Password

### Table
- table1 name: electricity_bill
	- id
		- generated value
		- uuid
			- example1: b5ce4b08-34ef-47aa-ad78-d3b5d59e62e2
			- example2: a5d732a7-030a-45e4-b136-f9e11f0a3070
	- name
		- varchar(20)
            - example1: "John"
            - example2: "Adam"	
            - example3: "Mary"
	- billed_at
		- timestamp
			- example1: 2021-01-31T00:00:00.000
			- example2: 2021-02-28T00:00:00.000
			- example3: 2021-03-31T00:00:00.000
	- country
		- varchar(10)
			- example1: "Thailand"
			- example2: "Japan"
			- example3: "Singapore"
	- unit
		- varchar(10)
			- example1: "50.57"
			- example2: "40.23"
			- example3: "60.00"
	- created_at
		- generated value
		- timestamp
			- example1: 2021-01-31T12:00:00.000

- table2 name: cost_per_unit   (all in USD)
	- id
		- generated value
		- uuid
			- example1: b5ce4b08-34ef-47aa-ad78-d3b5d59e62e2
			- example2: a5d732a7-030a-45e4-b136-f9e11f0a3070
	- country
		- varchar(10)
			- example1: "Thailand"
			- example2: "Japan"
			- example3: "Singapore"
	- cost_per_unit
		- varchar(10)
			- example1: "10"
			- example2: "20"
			- example3: "30"
	- created_at
		- generated value
		- timestamp
			- example1: 2021-01-31T12:00:00.000
### Mocked Data in table1
![MockedData](https://github.com/scottbright/alpha-exam-v4/blob/main/image/image1.png?raw=true)

### Mocked Data in table2
![MockedData](https://github.com/scottbright/alpha-exam-v4/blob/main/image/image2.png?raw=true)

## Tasks

### 1. Create REST API for showing sum of electricity bill data with cost calculation when input is name, startdate and enddate
description:
country = country to query
startdate = start period to include billed_at
enddate = end period to include billed_at

input
```
GET: localhost:8080/v2/cost?country=Thailand&startdate=20210101&enddate=20211231
```
example response when country, startdate and enddate found (***the type must be the same as example***)
```
{
    "header": {
        "totalUnit": 692.98,  //sum of units in data down below
        "costPerUnit": 10,    //cost per unit of Thailand
        "totalCost": 6929.80  //totalUnit x costPerUnit
    },
    "data": [  //array of data those have billedAt between startdate and enddate
        {
            "id": "c79172d7-98d3-5d4e-b553-14602366d1f4",
            "name": "John",
            "billedAt": "2021-04-15T00:00:00",
            "country": "Thailand",
            "unit": 72.19,
            "createdAt": "2021-04-15T00:00:00"
        },
        {
            "id": "6d06830e-f414-3147-a047-15f588f65dce",
            "name": "John",
            "billedAt": "2021-01-15T00:00:00",
            "country": "Thailand",
            "unit": 81.09,
            "createdAt": "2021-01-15T00:00:00"
        },
        {
            "id": "98853016-3a55-e643-9e2d-20bf7424dbc1",
            "name": "John",
            "billedAt": "2021-03-15T00:00:00",
            "country": "Thailand",
            "unit": 40.44,
            "createdAt": "2021-03-15T00:00:00"
        },
        {
            "id": "0e457a0b-540e-7e45-bc9c-41243e26f512",
            "name": "John",
            "billedAt": "2021-08-15T00:00:00",
            "country": "Thailand",
            "unit": 50.77,
            "createdAt": "2021-08-15T00:00:00"
        },
        {
            "id": "30a52c73-341f-7d48-bbb2-4d92d4409b04",
            "name": "John",
            "billedAt": "2021-09-15T00:00:00",
            "country": "Thailand",
            "unit": 55.66,
            "createdAt": "2021-09-15T00:00:00"
        },
        {
            "id": "2598f18f-7291-8e4f-9e24-722736348d65",
            "name": "John",
            "billedAt": "2021-06-15T00:00:00",
            "country": "Thailand",
            "unit": 78.22,
            "createdAt": "2021-06-15T00:00:00"
        },
        {
            "id": "8c24694b-b6e8-8545-a756-770f170a122f",
            "name": "John",
            "billedAt": "2021-05-15T00:00:00",
            "country": "Thailand",
            "unit": 43.16,
            "createdAt": "2021-05-15T00:00:00"
        },
        {
            "id": "b7ee9fac-ccf7-5744-95af-bd1db97fd3e3",
            "name": "John",
            "billedAt": "2021-02-15T00:00:00",
            "country": "Thailand",
            "unit": 61.37,
            "createdAt": "2021-02-15T00:00:00"
        },
        {
            "id": "2c767660-ee1d-1941-98c8-c5574a4bfb5b",
            "name": "John",
            "billedAt": "2021-11-15T00:00:00",
            "country": "Thailand",
            "unit": 63.78,
            "createdAt": "2021-11-15T00:00:00"
        },
        {
            "id": "2e996931-79df-1e4d-ad47-d1b406b7e882",
            "name": "John",
            "billedAt": "2021-12-15T00:00:00",
            "country": "Thailand",
            "unit": 49.98,
            "createdAt": "2021-12-15T00:00:00"
        },
        {
            "id": "3d734931-0381-e84a-84a4-dc892d9f76a6",
            "name": "John",
            "billedAt": "2021-07-15T00:00:00",
            "country": "Thailand",
            "unit": 40.44,
            "createdAt": "2021-07-15T00:00:00"
        },
        {
            "id": "9af8773a-5a83-3e4e-9876-f5ebfd0e5ab9",
            "name": "John",
            "billedAt": "2021-10-15T00:00:00",
            "country": "Thailand",
            "unit": 55.88,
            "createdAt": "2021-10-15T00:00:00"
        }
    ]
}

note: HTTP status 200
```
response when no data found
```
{
    "header": null,
    "data": null
}

note: HTTP status 200
```

### 2. sort data by Unit Descending

### 3. create some unit test