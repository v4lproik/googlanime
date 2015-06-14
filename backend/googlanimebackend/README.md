# Googlanime Backend
[![Circle CI](https://circleci.com/gh/v4lproik/googlanime/tree/master.svg?style=shield)](https://circleci.com/gh/v4lproik/googlanime/tree/master)

**Googlanime Backend** is the backend of the project [Googlanime](https://github.com/v4lproik/googlanime/)

This project aims to provide a clear and nice interface to whoever is seeking for anime/manga information.

## Features implemented

This is a the main core of the project. It provides a powerful JEE RESTful API that returns different types of information about animes, mangas, podcasts and so on.

## How to run the application

## Pre-requisites

- Install Maven 
- Install docker or boot2docker (MacOSX user only)
- JDK 1.7

## Installation

1. Clone the repository
2. `cd` to the created directory.
3. Add  to your /etc/hosts file `<ELASTICSEARCH_NODE_IP> es.googlanime`
4. Get all client dependencies through [bower](http://bower.io/): `bower install`
5. `cd` to backend/
6. `mvn compile jetty:run` to launch the server (Reachable at http://localhost:8080/ by default)
7. `sh scripts/mapping-anime.sh` to create the Elasticsearch mapping and to fill the anime and manga indexes with some data
8. Browse to a test page `http://localhost:8080/animes/?query=geass&fields=title&type=anime&render=mal`