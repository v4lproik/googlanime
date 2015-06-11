# Googlanime FrontEnd
[![Circle CI](https://circleci.com/gh/v4lproik/googlanime/tree/master.svg?style=shield)](https://circleci.com/gh/v4lproik/googlanime/tree/master)

**Googlanime Frontend** is the frontend of the project [Googlanime](https://github.com/v4lproik/googlanime/)

This project aims to provide a clear and nice interface to whoever is seeking for anime/manga information.

## Features implemented

This is a Simple interface using Ember.JS that allows a user to search for animes/mangas/podcasts. It implements the following features :

- Sign in with google/facebook/github using torii library
- Ember QueryParams and Ember data
- Communicates with [Googlanime backend](https://github.com/v4lproik/googlanime/tree/master/backend/)

## How to run the application

## Pre-requisites

- Install Node.js: [http://www.nodejs.org/](http://www.nodejs.org/). The used version can be found in the `package.json` file. 
- Install bower : `npm install -g bower`

## Installation

1. Clone the repository
2. `cd` to the created directory.
3. Get all Node dependencies through [npm](https://npmjs.org/): `npm install`
4. Get all client dependencies through [bower](http://bower.io/): `bower install`
5. Install ember-cli `npm install -g ember-cli`
6. Start the application `ember server`
7. Browse to http://localhost:4200/search

## Overview

![overview](https://raw.githubusercontent.com/v4lproik/googlanime/master/frontend/screenshots/overview-1.png)
