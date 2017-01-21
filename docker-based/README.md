This directory contains the docker version of our app.
To build the images use the following maven command : `mvn package docker:build` on each application.

### Legacy docker linking
2. exec `sudo docker container run -d --name=slip slip:latest`
3. exec `sudo docker container run -d -p 8080:8080 --name=poule --link=slip poule:latest`
4. go to [http://localhost:8080/]() to get the query graph

### Using a docker network
1. Create the network : `sudo docker network create --driver bridge my_network`
2. Verify that your network is created : `sudo docker network ls`
3. exec `sudo docker container run -d --name=slip --network=my_network slip:latest`
4. exec `sudo docker container run -d -p 8080:8080 --name=poule --network=my_network poule:latest`
5. go to [http://localhost:8080/]() to get the query graph

### Using compose
`docker-compose up -d`