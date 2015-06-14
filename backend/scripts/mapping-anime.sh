curl -XDELETE 'http://es.googlanime:9200/animes'
curl -XPUT 'http://es.googlanime:9200/animes/?pretty'
curl -XDELETE 'http://es.googlanime:9200/mangas'
curl -XPUT 'http://es.googlanime:9200/mangas/?pretty'
curl -XPUT http://es.googlanime:9200/animes/_mapping/anime -d '
{
      "anime": {
        "_id" : {
          "path" : "id"
        },
        "properties": {
          "@id": {
            "type": "long"
          },
          "adaptations": {
            "_parent": {
              "type": "anime"
            },
            "properties": {
              "@id": {
                "type": "long"
              },
              "id": {
                "type": "long"
              },
              "parent": {
                "type": "long"
              },
              "title": {
                "type": "string"
              },
              "type": {
                "type": "string"
              }
            }
          },
          "ageRating": {
            "type": "string"
          },
          "authors": {
            "properties": {
              "firstName": {
                "type": "string"
              },
              "job": {
                "type": "string"
              },
              "lastName": {
                "type": "string"
              }
            }
          },
          "characters": {
            "properties": {
              "firstName": {
                "type": "string"
              },
              "lastName": {
                "type": "string"
              },
              "role": {
                "type": "string"
              }
            }
          },
          "englishTitle": {
            "type": "string"
          },
          "episodeCount": {
            "type": "string"
          },
          "episodeLength": {
            "type": "string"
          },
          "finishedAiringDate": {
            "type": "string"
          },
          "genres": {
            "type": "string"
          },
          "id": {
            "type": "long"
          },
          "japaneseTitle": {
            "type": "string"
          },
          "others": {
            "_parent": {
              "type": "anime"
            },
            "properties": {
              "@id": {
                "type": "long"
              },
              "id": {
                "type": "long"
              },
              "parent": {
                "type": "long"
              },
              "title": {
                "type": "string"
              },
              "type": {
                "type": "string"
              }
            }
          },
          "popularity": {
            "type": "string"
          },
          "producers": {
            "type": "string"
          },
          "rank": {
            "type": "string"
          },
          "score": {
            "type": "string"
          },
          "sequels": {
            "_parent": {
            "type": "anime"
            },
            "properties": {
              "@id": {
                "type": "long"
              },
              "id": {
                "type": "long"
              },
              "parent": {
                "type": "long"
              },
              "title": {
                "type": "string"
              },
              "type": {
                "type": "string"
              }
            }
          },
          "showType": {
            "type": "string"
          },
          "sideStories": {
            "_parent": {
              "type": "anime"
            },
            "properties": {
              "@id": {
                "type": "long"
              },
              "id": {
                "type": "long"
              },
              "parent": {
                "type": "long"
              },
              "title": {
                "type": "string"
              },
              "type": {
                "type": "string"
              }
            }
          },
          "spinoff": {
            "_parent": {
              "type": "anime"
            },
            "properties": {
              "@id": {
                "type": "long"
              },
              "id": {
                "type": "long"
              },
              "parent": {
                "type": "long"
              },
              "title": {
                "type": "string"
              },
              "type": {
                "type": "string"
              }
            }
          },
          "startedAiringDate": {
            "type": "string"
          },
          "summaries": {
            "_parent": {
              "type": "anime"
            },
            "properties": {
              "@id": {
                "type": "long"
              },
              "id": {
                "type": "long"
              },
              "parent": {
                "type": "long"
              },
              "title": {
                "type": "string"
              },
              "type": {
                "type": "string"
              }
            }
          },
          "synopsis": {
            "type": "string"
          },
          "tags": {
            "type": "string"
          },
          "title": {
            "type": "string"
          },
          "type": {
            "type": "string"
          }
        }
      }
    }'

curl -XPUT http://es.googlanime:9200/mangas/_mapping/manga -d '
{
      "manga": {
        "_id" : {
          "path" : "id"
        },
        "properties": {
          "@id": {
            "type": "long"
          },
          "adaptations": {
            "_parent": {
              "type": "manga"
            },
            "properties": {
              "@id": {
                "type": "long"
              },
              "id": {
                "type": "long"
              },
              "parent": {
                "type": "long"
              },
              "title": {
                "type": "string"
              },
              "type": {
                "type": "string"
              }
            }
          },
          "ageRating": {
            "type": "string"
          },
          "authors": {
            "properties": {
              "firstName": {
                "type": "string"
              },
              "job": {
                "type": "string"
              },
              "lastName": {
                "type": "string"
              }
            }
          },
          "characters": {
            "properties": {
              "firstName": {
                "type": "string"
              },
              "lastName": {
                "type": "string"
              },
              "role": {
                "type": "string"
              }
            }
          },
          "englishTitle": {
            "type": "string"
          },
          "episodeCount": {
            "type": "string"
          },
          "episodeLength": {
            "type": "string"
          },
          "finishedAiringDate": {
            "type": "string"
          },
          "genres": {
            "type": "string"
          },
          "id": {
            "type": "long"
          },
          "japaneseTitle": {
            "type": "string"
          },
          "others": {
            "_parent": {
              "type": "manga"
            },
            "properties": {
              "@id": {
                "type": "long"
              },
              "id": {
                "type": "long"
              },
              "parent": {
                "type": "long"
              },
              "title": {
                "type": "string"
              },
              "type": {
                "type": "string"
              }
            }
          },
          "popularity": {
            "type": "string"
          },
          "producers": {
            "type": "string"
          },
          "rank": {
            "type": "string"
          },
          "score": {
            "type": "string"
          },
          "sequels": {
            "_parent": {
            "type": "manga"
            },
            "properties": {
              "@id": {
                "type": "long"
              },
              "id": {
                "type": "long"
              },
              "parent": {
                "type": "long"
              },
              "title": {
                "type": "string"
              },
              "type": {
                "type": "string"
              }
            }
          },
          "showType": {
            "type": "string"
          },
          "sideStories": {
            "_parent": {
              "type": "manga"
            },
            "properties": {
              "@id": {
                "type": "long"
              },
              "id": {
                "type": "long"
              },
              "parent": {
                "type": "long"
              },
              "title": {
                "type": "string"
              },
              "type": {
                "type": "string"
              }
            }
          },
          "spinoff": {
            "_parent": {
              "type": "manga"
            },
            "properties": {
              "@id": {
                "type": "long"
              },
              "id": {
                "type": "long"
              },
              "parent": {
                "type": "long"
              },
              "title": {
                "type": "string"
              },
              "type": {
                "type": "string"
              }
            }
          },
          "startedAiringDate": {
            "type": "string"
          },
          "summaries": {
            "_parent": {
              "type": "manga"
            },
            "properties": {
              "@id": {
                "type": "long"
              },
              "id": {
                "type": "long"
              },
              "parent": {
                "type": "long"
              },
              "title": {
                "type": "string"
              },
              "type": {
                "type": "string"
              }
            }
          },
          "synopsis": {
            "type": "string"
          },
          "tags": {
            "type": "string"
          },
          "title": {
            "type": "string"
          },
          "type": {
            "type": "string"
          }
        }
      }
    }'

curl 'http://localhost:8080/websites/import/store?from=mal&type=manga&id=9114&dependency=true' -H 'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8' -H 'Connection: keep-alive' -H 'Accept-Encoding: gzip, deflate, sdch' -H 'Cookie: request_method=GET; token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ZGUxNjM4My1hYjU3LTQxN2EtODMzYy0zNzQ5OWMyNTI4OTAiLCJzY29wZSI6WyJhbGwiXSwic3ViIjoxLCJpc3MiOjE0Mjk2OTExMDEsImV4cCI6MTQzNzU1MzUwMX0.XjJWOxWQSzwuNiFr3Ec_1kE3ScHmkwGIy_zRUor8wJc; _Hummingbird_session=c09IU0s4aUo5aE0xL2kxTGppcUR2VmR1djR1V1RGd3B3cFpTZjduS2VYOFBRVEpZOFBwSW11WVc3NE1vNmdOVU5aZVAzbHVIbmo4enFVVGhmSCtQcFFTUytHVzhyM0VVMEd6YU4yV0ZxNjduU2NqR0tkYWVJVkRSMkxOcFl1YU1kOFRteWo3azh0VXJ1aC9hMFg3RE1zcXF1VzFoVk51WlpvUTFXdTlYemduQlgwbGNhc1Y0NzdXWHRCZnZMUDJzcGloam95dWttanByd2twYzViRE5ZSUFrWk9tYmRHN0g3TGVlVGgwUWNaeC9qdE1WT2RzM0xHQTRBNXlnMjdlYUZRYTQrNitOc2sxNDQwYjA1ZVgycmVsTDlveU1VWkpKaEUrWng4dkNLbnF5N25Nb0JIYzFDdjBjdGZhZ0JzTGN6QzVoeStTREl6bER6L1YzVXFKemIyNkxJSU9rck5OK0Y0S3J0UXhZamJXV1JjNnpsREp3bVJUUFFMaHFOelB5LS03TEN5Y0NIQ05oRmxkZlhTd2dudUNBPT0%3D--1b7e0374ae71a193ce687c9a85183ff5c905ec0a; __profilin=p%3Dt' -H 'Accept-Language: en-US,en;q=0.8,fr;q=0.6' -H 'User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.81 Safari/537.36' --compressed
curl 'http://localhost:8080/websites/import/store?from=mal&type=manga&id=24' -H 'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8' -H 'User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.81 Safari/537.36' --compressed
curl 'http://localhost:8080/websites/import/store?from=mal&type=anime&id=1575' -H 'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8' -H 'User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.81 Safari/537.36' --compressed
