# Transformers API

![](https://pbs.twimg.com/profile_images/616823034234728448/RV2o9Lpn_400x400.jpg)

The Transformers are at war and you are in charge of settling the score! You’re to create an API
that evaluates who wins a fight between the Autobots and the Decepticons.

## Description

### Main Functionality

* Create a Transformer
* Update a Transformer
* Delete a Transformer
* List Transformers
* Given a list of Transformer IDs, determine the winning team


### Transformer Definition

Each Transformer has the following criteria (ranked from 1 to 10) on their tech spec:
* Strength
* Intelligence
* Speed
* Endurance
* Rank
* Courage
* Firepower
* Skill

Each Transformer must either be an Autobot or a Decepticon

![](https://findicons.com/files/icons/1177/transformers_x_vol_3/256/heroic_autobots.png)
![](https://i.pinimg.com/originals/e5/ef/e9/e5efe9d4c441c6b39919cd612a4c5440.png)


### Determining winning team

Input a list of Transformer IDs and based on input returns:
1. The number of battles
2. The winning team
3. The surviving members of the losing team

### Basic Rules

* The transformers are split into two teams based on if they are Autobots or Decepticons
* The teams should be sorted by rank and faced off one on one against each other in order to determine a victor, the loser is eliminated.
* A battle between opponents uses the following rules:
 - If any fighter is down 4 or more points of courage and 3 or more points of
strength compared to their opponent, the opponent automatically wins the
face-off regardless of overall rating (opponent has ran away)
- Otherwise, if one of the fighters is 3 or more points of skill above their opponent,
they win the fight regardless of overall rating
* The winner is the Transformer with the highest overall rating
* In the event of a tie, both Transformers are considered destroyed
* Any Transformers who don’t have a fight are skipped (i.e. if it’s a team of 2 vs. a team of
1, there’s only going to be one battle)
* The team who eliminated the largest number of the opposing team is the winner

### Special Rules

* Any Transformer named Optimus Prime or Predaking wins his fight automatically
regardless of any other criteria
* In the event either of the above face each other (or a duplicate of each other), the game
immediately ends with all competitors destroyed

### Example:

For example, given the following input:
>Soundwave, D, 8,9,2,6,7,5,6,10

>Bluestreak, A, 6,6,7,9,5,2,9,7

>Hubcap: A, 4,4,4,4,4,4,4,4

The output should be:

> 1 battle

> Winning team (Decepticons): Soundwave

> Survivors from the losing team (Autobots): Hubcap

#### Let's start the battle!

![](https://64.media.tumblr.com/0eb97149d82b8e13d3d9f6c5b2795ca5/tumblr_mpy4lqT9RM1qmi47qo1_500.gif)



## Build Project

The first step is build project to create .jar

Open the terminal and follow the steps:

```bash
$ cd project
$ mvn clean package
```

## Run on Docker container

```bash
$ cd project
$ docker build -t transformers-api-1.0.0 .
$ docker run -d -p 8080:8080 -t transformers-api-1.0.0
```

Docker is running on 8080 port.

## Usage


Swagger available while the docker is running


[![View in Swagger](https://raw.githubusercontent.com/jessemillar/view-in-swagger-button/03073fe128d35adfcad35b03b853aa76cfdd9002/button.svg)](http://localhost:8080/transformers-api/swagger-ui/index.html?configUrl=/transformers-api/api-docs/swagger-config#/)
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/bfe430ea7efd0b50461e)

