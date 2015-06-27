# Googlanime Backend
[![Circle CI](https://circleci.com/gh/v4lproik/googlanime/tree/master.svg?style=shield)](https://circleci.com/gh/v4lproik/googlanime/tree/master)

**Googlanime Backend** is the backend of [Googlanime](https://github.com/v4lproik/googlanime/) project.

This project aims to provide a clear and nice interface to whoever is seeking for anime/manga information.

## Features implemented

This is a the main core of the project. It provides a powerful JEE RESTful API that returns different types of information about animes, mangas, podcasts and so on.

## Pre-requisites

- Install Maven 
- Install docker or boot2docker (MacOSX user only)
- JDK 1.7

## Installation

1. Clone the repository
2. `cd` to the created directory.
3. Add  to your /etc/hosts file `<ELASTICSEARCH_NODE_IP> es.googlanime`
4. `cd` to backend/
5. `mvn clean compile jetty:run -Dspring.profiles.active="test"` to launch the server (Reachable at http://localhost:8080/ by default)
6. `sh scripts/fill-elasticsearch.sh` to fill elasticsearch with some data
7. Browse to a test page `http://localhost:8080/animes/?query=geass&fields=title&type=anime&render=mal`