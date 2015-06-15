#!/bin/bash
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

