# Transformers API

![](https://image.flaticon.com/icons/png/128/813/813484.png)

The Transformers are at war and you are in charge of settling the score! You’re to create an API
that evaluates who wins a fight between the Autobots and the Decepticons.

## Build Project

The first step is to build project to create .jar

Open the terminal and follow the steps:

```bash
$ cd project
$ mvn clean package
```

## Run on Docker container

```bash
$ cd project
$ docker run -d -p 8080:8080 -t transformers-api-1.0.0
```

Docker is running on 8080 port.

## Usage

[Swagger](localhost:8080/transformers-api/swagger-ui.html) available while the docker is running


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
