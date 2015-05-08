import DS from 'ember-data';
import App from 'ember';

App.ApplicationAdapter = DS.FixtureAdapter.extend();

let Anime = DS.Model.extend({
  slug: DS.attr('string'),
  canonicalTitle: DS.attr('string'),
  englishTitle: DS.attr('string'),
  romajiTitle: DS.attr('string'),
  japaneseTitle: DS.attr('string'),
  synopsis: DS.attr('string'),
  startedAiringDate: DS.attr('string'),
  finishedAiringDate: DS.attr('string'),
  youtubeVideoId: DS.attr('string'),
  ageRating: DS.attr('string'),
  episodeCount: DS.attr('string'),
  episodeLength: DS.attr('string'),
  showType: DS.attr('string'),
  posterImage: DS.attr('string'),
  coverImage: DS.attr('string'),
  communityRating: DS.attr('string'),
  genres: DS.hasMany('genre'),
  links: DS.attr('string')
});

Anime.reopenClass({
  FIXTURES: [
    {
      "id":2,
      "titles":
          {
            "canonical":"Cowboy Bebop: Tengoku no Tobira",
            "english":"Cowboy Bebop: Knockin' on Heaven's Door",
            "romaji":"Cowboy Bebop: Tengoku no Tobira",
            "japanese":null
          }
        ,
      "slug":"cowboy-bebop-tengoku-no-tobira",
      "synopsis":"As the Cowboy Bebop crew travels the stars, they learn of the largest bounty yet, a huge 300 million Woolongs. Apparently, someone is wielding a hugely powerful chemical weapon, and of course the authorities are at a loss to stop it. The war to take down the most dangerous criminal yet forces the crew to face a true madman, with bare hope to succeed.\r\n(Source: ANN) ",
      "startedAiringDate":"2001-09-01",
      "finishedAiringDate":null,
      "youtubeVideoId":null,
      "ageRating":"R17+",
      "episodeCount":1,
      "episodeLength":114,
      "showType":"Movie",
      "poster_image":"https://static.hummingbird.me/anime/poster_images/000/000/002/large/2.jpg?1408440446",
      "cover_image":"https://static.hummingbird.me/anime/cover_images/000/000/002/thumb/408580.jpg?1417213010",
      "communityRating":4.3166828285493,
      "genres":["Action","Drama","Sci-Fi","Mystery"],
      "producers":["Bones","Sunrise","Bandai Visual"],
      "bayesian_rating":4.3166828285493
      /*
       "links":
       {
       "gallery_images":[],
       "episodes":[27]},
       "linked":
       {
       "gallery_images":[],
       "episodes":
       [
       {
       "id":27,
       "title":"Episode 1",
       "synopsis":null,
       "airdate":null,
       "number":1,
       "season_number":null
       ]
       }
       */
    },
    {

      "id": 3,
      "titles": {
        "canonical": "Trigun",
        "english": null,
        "romaji": "Trigun",
        "japanese": "トライガン"
      },
      "slug": "trigun",
      "synopsis": "Trigun takes place in the distant future on a deserted planet. Vash the Stampede is a gunfighter with a legend so ruthless that he has a $$60,000,000,000 bounty on his head. Entire towns are evacuated upon hearing rumors of his arrival. However, the real Vash the Stampede is not the same man that rumor portrays him to be. The enigmatic and conflicted lead character in Trigun is actually more heroic in nature, and at times a complete and utter idiot.",
      "startedAiringDate": "1998-04-01",
      "finishedAiringDate": "1998-09-30",
      "youtubeVideoId": "GyQn5USYuZE",
      "ageRating": "PG13",
      "episodeCount": 26,
      "episodeLength": 24,
      "showType": "TV",
      "poster_image": "https://static.hummingbird.me/anime/poster_images/000/000/003/large/3.jpg?1416236341",
      "cover_image": "https://static.hummingbird.me/anime/cover_images/000/000/003/thumb/anime-trigun_403491.jpg?1416236342",
      "communityRating": 4.24390904999942,
      "genres": [
        "Action",
        "Comedy",
        "Sci-Fi"
      ],
      "producers": [
        "Madhouse Studios",
        "Geneon Universal Entertainment",
        "FUNimation Entertainment"
      ],
      "bayesian_rating": 4.24390904999942,
      "links": {
        "gallery_images": [
          71
        ],
        "episodes": [
          52,
          51,
          49,
          48,
          47,
          45,
          44,
          43,
          42,
          41,
          39,
          38,
          37,
          35,
          33,
          32,
          31,
          30,
          29,
          28,
          34,
          36,
          40,
          46,
          50,
          53
        ]

      },
      "linked": {
        "gallery_images": [
          {
            "id": 71,
            "thumb": "https://static.hummingbird.me/gallery_images/images/000/000/071/thumb/71.jpg?1375597966",
            "original": "https://static.hummingbird.me/gallery_images/images/000/000/071/original/71.jpg?1375597966"
          }
        ],
        "episodes": [
          {
            "id": 52,
            "title": "Live Through",
            "synopsis": "Vash has suffered enough for an eternity. As decision-making time approaches, the town's people decide to lynch him and he may not try to stop them.",
            "airdate": "1998-09-23",
            "number": 25,
            "season_number": 1
          },
          {
            "id": 51,
            "title": "Sin",
            "synopsis": "As Vash ventures on he finally confronts Legato, who proves that the winner in not necessarily the last man standing.",
            "airdate": "1998-09-16",
            "number": 24,
            "season_number": 1
          },
          {
            "id": 49,
            "title": "Alternative",
            "synopsis": "Many people have been disappearing, whole towns even, and people are getting paranoid. Vash, Wolfwood and the girls are stuck outside and can't come in because the guards are too afraid to let them. They find shelter in an orphanage of sorts, and when trouble starts, a surprising adversary helps Wolfwood see the error in his ways.",
            "airdate": "1998-09-02",
            "number": 22,
            "season_number": 1
          },
          {
            "id": 48,
            "title": "Out of Time",
            "synopsis": "Things worsen at the city in the clouds as two more Gung-Ho Guns appear, and what they're after might bring grim consequences.",
            "airdate": "1998-08-26",
            "number": 21,
            "season_number": 1
          },
          {
            "id": 47,
            "title": "Flying Ship",
            "synopsis": "Vash takes the nearest \"ride\" to a city in the clouds, a relic from the times of old, to meet up with his old friends. Wolfwood follows him and joins him in the friendly visit, but there's also another who followed them both...",
            "airdate": "1998-08-19",
            "number": 20,
            "season_number": 1
          },
          {
            "id": 45,
            "title": "Goodbye for Now",
            "synopsis": "Two years after Vash disappeared in the ruins of the city, two years after Milly and Meryl were forced to run away from the devastating arm, two years after Vash was declared the first human act of god by the insurance company because of how unstoppable he is - a quiet town is surrounded by bounty hunters, their leader claims to be Vash the Stampede, and Wolfwood is besieged inside! All seems to be lost until a young blond man who has been living in that town for two years now, known only as Erics to it's residents, pops out to the rescue...",
            "airdate": "1998-08-05",
            "number": 18,
            "season_number": 1
          },
          {
            "id": 44,
            "title": "Rem Saverem",
            "synopsis": "In a very unorthodox episode, many explanations about the mystery surrounding Vash the Stampede are put forth. Where did he come from? Who is he? What is he fighting for? ",
            "airdate": "1998-07-29",
            "number": 17,
            "season_number": 1
          },
          {
            "id": 43,
            "title": "Fifth Moon",
            "synopsis": "Vash is challenged to a duel inside an inhabited city with two of the Gung-Ho Guns. The plot thickens as Legato butts in and unleashes Vash's horrible secret.",
            "airdate": "1998-07-22",
            "number": 16,
            "season_number": 1
          },
          {
            "id": 42,
            "title": "Demon's Eye",
            "synopsis": "Things get serious as Legato and his Gung-Ho Guns stir up trouble. Vash will have no choice but to roughen up to answer the challenge.",
            "airdate": "1998-07-15",
            "number": 15,
            "season_number": 1
          },
          {
            "id": 41,
            "title": "Little Arcadia",
            "synopsis": "In this almost completely Vash free episode, the two insurance girls need to help an old farmer save his beautiful grove from a gang who wants to take it away from him, in which the farmer's son is a member.",
            "airdate": "1998-07-08",
            "number": 14,
            "season_number": 1
          },
          {
            "id": 39,
            "title": "Diablo",
            "synopsis": "Vash's past catches up to him as Legato Bluesummers, new nemesis, frames him for murder and sends an assassin to destroy him - and as push comes to shove Vash exposes a new side in his personality.",
            "airdate": "1998-06-24",
            "number": 12,
            "season_number": 1
          },
          {
            "id": 38,
            "title": "Escape from Pain",
            "synopsis": "In this Wolfwood-Milly-centered episode, the two encounter a couple on the run while Vash is hired to hunt them down.",
            "airdate": "1998-06-17",
            "number": 11,
            "season_number": 1
          },
          {
            "id": 37,
            "title": "Quick Draw",
            "synopsis": "In a noble attempt, Vash and Wolfwood join a quick drawing competition to help a single mother and her daughter.",
            "airdate": "1998-06-10",
            "number": 10,
            "season_number": 1
          },
          {
            "id": 35,
            "title": "And Between the Wasteland and Sky",
            "synopsis": "The adventure on the speeding sand-steamer continues as Vash reveals another aspect in his personality and his young friend is faced with the dilemma of speaking up to a grown-up.",
            "airdate": "1998-05-20",
            "number": 8,
            "season_number": 1
          },
          {
            "id": 33,
            "title": "Lost July",
            "synopsis": "After the town's folk gather enough money for a repair crew to be called for to repair their power plant, Vash is hired as a body guard for the head engineer - an attractive woman who seems to have a particular interest in the gunslinger. An interest which might be accidentally mistaken to be affection.",
            "airdate": "1998-05-06",
            "number": 6,
            "season_number": 1
          },
          {
            "id": 32,
            "title": "Hard Puncher",
            "synopsis": "An entire town has gone rampage searching for the $$60 billion head of Vash The Stampede in the hopes of hunting him for the reward. The humanoid typhoon will have to make a choice, will he reveal what he's really made of?",
            "airdate": "1998-04-29",
            "number": 5,
            "season_number": 1
          },
          {
            "id": 31,
            "title": "Love and Peace",
            "synopsis": "Vash is stuck between a rock and a hard place as he accidentally waltzes into a live robbery scene with his headphones on, unable to hear the police officers yelling at him to stay away. The plot thickens, though, as the robbery is revealed to be of the more personal sort...",
            "airdate": "1998-04-22",
            "number": 4,
            "season_number": 1
          },
          {
            "id": 30,
            "title": "Peace Maker",
            "synopsis": "Vash is forced to befriend a drunk as a city comes under attack by bandits whose leader claims he is Vash the Stampede.",
            "airdate": "1998-04-15",
            "number": 3,
            "season_number": 1
          },
          {
            "id": 29,
            "title": "Truth of Mistake",
            "synopsis": "In a town suffering from drought, Vash is hired to be the bodyguard of the only man who has water in the arid desert.",
            "airdate": "1998-04-08",
            "number": 2,
            "season_number": 1
          },
          {
            "id": 28,
            "title": "The $$60 Billion Man",
            "synopsis": "There are many rumors circulating about Vash the Stampede, but few people have actually seen him. Confusion follows as a couple of bounty hunters and two insurance girls separately try to find the real Vash, but only one of the bounty hunters guesses right. The other bounty hunter and the two girls think the first bounty hunter is Vash and even more confusion comes. In the end, the girls can't believe that the real Vash is who he actually is, because he's too nice and silly to match up with the rumors about \"Vash the Stampede.\"",
            "airdate": "1998-04-01",
            "number": 1,
            "season_number": 1
          },
          {
            "id": 34,
            "title": "B.D.N.",
            "synopsis": "Vash is joined with a young boy whom he's forced to take under his wings when the steamer comes under attack.",
            "airdate": "1998-05-13",
            "number": 7,
            "season_number": 1
          },
          {
            "id": 36,
            "title": "Murder Machine",
            "synopsis": "A simple bus trip across the dunes goes wrong when mechanical spiders threaten the passengers. Vash meets a traveling priest who seems more than meets the eye.",
            "airdate": "1998-05-27",
            "number": 9,
            "season_number": 1
          },
          {
            "id": 40,
            "title": "Vash the Stampede",
            "synopsis": "Meryl writes back to the insurance company a summary of the events preceding this episode in an attempt to decipher the character of Vash The Stampede.",
            "airdate": "1998-07-01",
            "number": 13,
            "season_number": 1
          },
          {
            "id": 46,
            "title": "Hang Fire",
            "synopsis": "News of Vash's reappearance reach the Bernardelli Insurance Company. Meryl and Milly go on the road to search for the Humanoid Typhoon while he steps into a bloody dispute between two families.",
            "airdate": "1998-08-12",
            "number": 19,
            "season_number": 1
          },
          {
            "id": 50,
            "title": "Paradise",
            "synopsis": "Wolfwood's past catches up with him as the priest acknowledges he has a few sins to confess himself.",
            "airdate": "1998-09-09",
            "number": 23,
            "season_number": 1
          },
          {
            "id": 53,
            "title": "Under the Sky So Blue",
            "synopsis": "Vash confronts his brother Knives in a big climax, and all of the remaining questions about their pasts are answered.",
            "airdate": "1998-09-30",
            "number": 26,
            "season_number": 1
          }
        ]
      }
    },

    {
      "id": 5,
      "titles": {
        "canonical": "Beet the Vandel Buster",
        "english": null,
        "romaji": "Beet the Vandel Buster",
        "japanese": "Beet the Vandel Buster"
      },
      "slug": "beet-the-vandel-buster",
      "synopsis": "It is the dark century and the people are suffering under the rule of the devil, Vandel, who is able to manipulate monsters. The Vandel Busters are a group of people who hunt these devils, and among them, the Zenon Squad is known to be the strongest busters on the continent. A young boy, Beet, dreams of joining the Zenon Squad. However, one day, as a result of Beet's fault, the Zenon squad was defeated by the devil, Beltose. The five dying busters sacrificed their life power into their five weapons, Saiga. After giving their weapons to Beet, they passed away. Years have passed since then and the young Vandel Buster, Beet, begins his adventure to carry out the Zenon Squad's will to put an end to the dark century. ",
      "startedAiringDate": "2004-09-30",
      "finishedAiringDate": "2005-09-29",
      "youtubeVideoId": null,
      "ageRating": "PG",
      "episodeCount": 52,
      "episodeLength": 23,
      "showType": "TV",
      "poster_image": "https://static.hummingbird.me/anime/poster_images/000/000/005/large/5.jpg?1408440453",
      "cover_image": "/cover_images/thumb/missing.png",
      "communityRating": 3.41196997646994,
      "genres": [
        "Adventure",
        "Supernatural",
        "Fantasy"
      ],
      "producers": [
        "Toei Animation"
      ],
      "bayesian_rating": 3.41196997646994,
      "links": {
        "gallery_images": [],
        "episodes": [
          130,
          129,
          125,
          124,
          123,
          120,
          119,
          114,
          113,
          112,
          111,
          110,
          109,
          107,
          104,
          103,
          101,
          99,
          97,
          96,
          94,
          92,
          90,
          88,
          86,
          85,
          83,
          82,
          80,
          81,
          84,
          87,
          89,
          91,
          93,
          95,
          98,
          100,
          102,
          105,
          106,
          108,
          115,
          116,
          117,
          118,
          121,
          122,
          126,
          127,
          128,
          131
        ]
      },

      "linked": {
        "gallery_images": [],
        "episodes": [
          {
            "id": 130,
            "title": "Captured City! Defend Port Termits!",
            "synopsis": null,
            "airdate": null,
            "number": 51,
            "season_number": 1
          },
          {
            "id": 129,
            "title": "Beet And Kiss! The Symbol Of Friendship!",
            "synopsis": null,
            "airdate": null,
            "number": 50,
            "season_number": 1
          },
          {
            "id": 125,
            "title": "Awake, Busters! Reach for the Light of Hope",
            "synopsis": null,
            "airdate": null,
            "number": 46,
            "season_number": 1
          },
          {
            "id": 124,
            "title": "The Last of Grunide! Farewell, Dark Green Tactician",
            "synopsis": null,
            "airdate": null,
            "number": 45,
            "season_number": 1
          },
          {
            "id": 123,
            "title": "Miracle Team! Strike! The Miracle Triple Attack",
            "synopsis": null,
            "airdate": null,
            "number": 44,
            "season_number": 1
          },
          {
            "id": 120,
            "title": "Peak Infuriation! The Roar of the Thrashing Beast",
            "synopsis": null,
            "airdate": null,
            "number": 41,
            "season_number": 1
          },
          {
            "id": 119,
            "title": "Bloody Beast! The True Form of Grunide",
            "synopsis": null,
            "airdate": null,
            "number": 40,
            "season_number": 1
          },
          {
            "id": 114,
            "title": "Ganeel! Dark Currency is Everything in This World",
            "synopsis": null,
            "airdate": null,
            "number": 35,
            "season_number": 1
          },
          {
            "id": 113,
            "title": "Lightbolt Grasper! Come Forth, Lightning of Beauty",
            "synopsis": null,
            "airdate": null,
            "number": 34,
            "season_number": 1
          },
          {
            "id": 112,
            "title": "Milfa! BB of Love and Justice Reappears",
            "synopsis": null,
            "airdate": null,
            "number": 33,
            "season_number": 1
          },
          {
            "id": 111,
            "title": "The Great Escape! While Beet is Asleep...",
            "synopsis": null,
            "airdate": null,
            "number": 32,
            "season_number": 1
          },
          {
            "id": 110,
            "title": "Batton! A Lone Sniper",
            "synopsis": null,
            "airdate": null,
            "number": 31,
            "season_number": 1
          },
          {
            "id": 109,
            "title": "Tensei of Ice Shockwave! Change the Tears into Strength",
            "synopsis": null,
            "airdate": null,
            "number": 30,
            "season_number": 1
          },
          {
            "id": 107,
            "title": "Meigeki of Grand Flame Stream! The Time Limit of Decision",
            "synopsis": null,
            "airdate": null,
            "number": 28,
            "season_number": 1
          },
          {
            "id": 104,
            "title": "Vandel castle! Crime and punishment of betrayal!",
            "synopsis": null,
            "airdate": "2005-03-24",
            "number": 25,
            "season_number": 1
          },
          {
            "id": 103,
            "title": "Re-game (return match)!",
            "synopsis": null,
            "airdate": "2005-03-17",
            "number": 24,
            "season_number": 1
          },
          {
            "id": 101,
            "title": "Hula mortar key",
            "synopsis": null,
            "airdate": "2005-03-03",
            "number": 22,
            "season_number": 1
          },
          {
            "id": 99,
            "title": "Crash! Kissu's last defense game",
            "synopsis": null,
            "airdate": "2005-02-17",
            "number": 20,
            "season_number": 1
          },
          {
            "id": 97,
            "title": "Attack the hideout of the demon!",
            "synopsis": null,
            "airdate": "2005-02-03",
            "number": 18,
            "season_number": 1
          },
          {
            "id": 96,
            "title": "Super heroine of electric shock!",
            "synopsis": null,
            "airdate": "2005-01-27",
            "number": 17,
            "season_number": 1
          },
          {
            "id": 94,
            "title": "Decisive battle! Beet vs. Beltzore",
            "synopsis": null,
            "airdate": "2005-01-13",
            "number": 15,
            "season_number": 1
          },
          {
            "id": 92,
            "title": "Silent Glaive! The Sword of Gale Dances!",
            "synopsis": null,
            "airdate": "2004-12-23",
            "number": 13,
            "season_number": 1
          },
          {
            "id": 90,
            "title": "Saiga! Necessary shooting sword!",
            "synopsis": null,
            "airdate": "2004-12-09",
            "number": 11,
            "season_number": 1
          },
          {
            "id": 88,
            "title": "Thread! Lonely assassin!",
            "synopsis": null,
            "airdate": "2004-11-25",
            "number": 9,
            "season_number": 1
          },
          {
            "id": 86,
            "title": "Mel world! Ability fang collector",
            "synopsis": null,
            "airdate": "2004-11-11",
            "number": 7,
            "season_number": 1
          },
          {
            "id": 85,
            "title": "Beet the buster is coming",
            "synopsis": null,
            "airdate": "2004-11-04",
            "number": 6,
            "season_number": 1
          },
          {
            "id": 83,
            "title": "Burning Lance! Gain the ability fang of the thermal flame",
            "synopsis": "Beet comes to the rescue to save Poala. He defeats Grineed with the Burning Lance.",
            "airdate": "2004-10-21",
            "number": 4,
            "season_number": 1
          },
          {
            "id": 82,
            "title": "Poala! Duel!",
            "synopsis": "Poala find herself fighting a hard Vandel named Grineed.",
            "airdate": "2004-10-14",
            "number": 3,
            "season_number": 1
          },
          {
            "id": 80,
            "title": "Beet, Boys be Ambitious!",
            "synopsis": "In the beginning, Beet is shown defeating a Vandel. When a boy asks him how he became a vandel, he started to tell him. Back three years ago, Beet made a Buster's promise or got braded. He wanted to be a buster and join Zenon's warriors. When Beet got rejected again, he showed them the braid. However, Poala, a chilhood friend of Beet's, stopped him and took him away. She asked him why he was so reckless, but he told her that being a buster was his dream and marrying her was part of it too. She punced him and walked away.\nWhen Beet started his training later on to level up, the Zenon warriors tried to prove that Beet was not ready to be a buster. When Beet tries to fight the clam monsters, the weakest monster, he fails. Zenon tells Beet how to defeat the monster and Beet kills the monster. Zenon asked him whats so good about being a buster. Beet tells him that it is justice.\nAt sunset, a vandel comes toward the city. Poala is told to tell the Zenon warriors while Beet tries to take on t",
            "airdate": "2004-09-30",
            "number": 1,
            "season_number": 1
          },
          {
            "id": 81,
            "title": "Zenon warrior group! End of proudness comes fighting to the death...",
            "synopsis": "Zenon and he comrades stay in the town. Trouble alerts that Beltorze, king of tragedy, is coming. Zenon and his comrades brace themselves. Beltorze happen to win the round leaving Beet with the Sagai of all five Zenon warriors.",
            "airdate": "2004-10-07",
            "number": 2,
            "season_number": 1
          },
          {
            "id": 84,
            "title": "Beet warriors! Onward to a Great Journey!",
            "synopsis": "Beet and Poala finally leave their hometown and embrace a new journey.",
            "airdate": "2004-10-28",
            "number": 5,
            "season_number": 1
          },
          {
            "id": 87,
            "title": "Saiga! The soul!",
            "synopsis": null,
            "airdate": "2004-11-18",
            "number": 8,
            "season_number": 1
          },
          {
            "id": 89,
            "title": "Attacking Satoshi military officer",
            "synopsis": null,
            "airdate": "2004-12-02",
            "number": 10,
            "season_number": 1
          },
          {
            "id": 91,
            "title": "Slade! Fang Ace!",
            "synopsis": null,
            "airdate": "2004-12-16",
            "number": 12,
            "season_number": 1
          },
          {
            "id": 93,
            "title": "Awaking the power",
            "synopsis": null,
            "airdate": "2005-01-06",
            "number": 14,
            "season_number": 1
          },
          {
            "id": 95,
            "title": "Horizon of black! To agitated new world!",
            "synopsis": null,
            "airdate": "2005-01-20",
            "number": 16,
            "season_number": 1
          },
          {
            "id": 98,
            "title": "Threat of the Planet Earth",
            "synopsis": null,
            "airdate": "2005-02-10",
            "number": 19,
            "season_number": 1
          },
          {
            "id": 100,
            "title": "Beltorze and the three star Vandel",
            "synopsis": null,
            "airdate": "2005-02-24",
            "number": 21,
            "season_number": 1
          },
          {
            "id": 102,
            "title": "Kiss! Oath of meeting again and that day",
            "synopsis": null,
            "airdate": "2005-03-10",
            "number": 23,
            "season_number": 1
          },
          {
            "id": 105,
            "title": "Saiga! Release gun!",
            "synopsis": null,
            "airdate": "2005-03-31",
            "number": 26,
            "season_number": 1
          },
          {
            "id": 106,
            "title": "Rozzgoat! The One Who Follows the Power",
            "synopsis": null,
            "airdate": null,
            "number": 27,
            "season_number": 1
          },
          {
            "id": 108,
            "title": "Fight Back! Genious Kiss of Burning Life",
            "synopsis": null,
            "airdate": null,
            "number": 29,
            "season_number": 1
          },
          {
            "id": 115,
            "title": "Warship Tortoise! Chase the Mobile Fortress",
            "synopsis": null,
            "airdate": null,
            "number": 36,
            "season_number": 1
          },
          {
            "id": 116,
            "title": "Zenir! The Soundless Fear",
            "synopsis": null,
            "airdate": null,
            "number": 37,
            "season_number": 1
          },
          {
            "id": 117,
            "title": "Jiiku! The Path that Busters Follow",
            "synopsis": null,
            "airdate": null,
            "number": 38,
            "season_number": 1
          },
          {
            "id": 118,
            "title": "Charge! The Nightmare of Grunide`s Castle",
            "synopsis": null,
            "airdate": null,
            "number": 39,
            "season_number": 1
          },
          {
            "id": 121,
            "title": "Boltic Axe! The Raging Axe of Thunder",
            "synopsis": null,
            "airdate": null,
            "number": 42,
            "season_number": 1
          },
          {
            "id": 122,
            "title": "Devastation! The Last Counterattack",
            "synopsis": null,
            "airdate": null,
            "number": 43,
            "season_number": 1
          },
          {
            "id": 126,
            "title": "Wanted! Kiss is Under Arrest!",
            "synopsis": null,
            "airdate": null,
            "number": 47,
            "season_number": 1
          },
          {
            "id": 127,
            "title": "Emergency! Dazed Milfa",
            "synopsis": null,
            "airdate": null,
            "number": 48,
            "season_number": 1
          },
          {
            "id": 128,
            "title": "Sled! The Independent Buster!",
            "synopsis": null,
            "airdate": null,
            "number": 49,
            "season_number": 1
          },
          {
            "id": 131,
            "title": "The Last Battle! Farewell, Black Horizon!!",
            "synopsis": null,
            "airdate": null,
            "number": 52,
            "season_number": 1
          }
        ]
      }
    },

    {
      "id":78,
      "titles":{
        "canonical":"Mai-Otome",
        "english":"My ZHiME",
        "romaji":"Mai-Otome",
        "japanese":null
      },
      "slug":"mai-otome",
      "synopsis":"Arika Yumemiya has traveled far in search of her goal: the prestigious Gualderobe Academy. This is the school where young girls are trained to become Otomes, protectors of royal leaders throughout the lands. Here, Arika makes plenty of friends, but some enemies know something about her past that she does not. Nevertheless, her spirit and determination will keep pushing her forward. --- Adapted from its predecessor, Mai-HiME, this series is not a direct sequel, but an alternate universe setting featuring some of the Mai-HiME cast (though with different personalities). \n(Source: ANN)",
      "startedAiringDate":"2005-10-07",
      "finishedAiringDate":"2006-03-31",
      "youtubeVideoId":null,
      "ageRating":"PG13",
      "episodeCount":26,
      "episodeLength":24,
      "showType":"TV",
      "poster_image":"https://static.hummingbird.me/anime/poster_images/000/000/078/large/78.jpg?1408440651",
      "cover_image":"https://static.hummingbird.me/anime/cover_images/000/000/078/thumb/1173122209528.jpg?1419346965",
      "communityRating":3.64105504069979,
      "genres":[
        "Comedy",
        "Drama",
        "Magic",
        "Fantasy"
      ],
      "producers":[
        "Sunrise",
        "Bandai Visual",
        "Lantis",
        "Bandai Entertainment",
        "TV Tokyo Music"
      ],
      "bayesian_rating":3.64105504069979,
      "links":{
        "gallery_images":[
          2223,
          2224,
          2225,
          2226
        ],
        "episodes":[
          169569,
          2451,
          2450,
          2449,
          2448,
          2446,
          2445,
          2443,
          2442,
          2441,
          2440,
          2438,
          2437,
          2435,
          2434,
          2433,
          2432,
          2431,
          2429,
          2428,
          2427,
          2426,
          2430,
          2436,
          2439,
          2444,
          2447
        ]

      },
      "linked":{
        "gallery_images":[
          {
            "id":2223,
            "thumb":"https://static.hummingbird.me/gallery_images/images/000/002/223/thumb/2223.jpeg?1375600047",
            "original":"https://static.hummingbird.me/gallery_images/images/000/002/223/original/2223.jpeg?1375600047"
          },
          {
            "id":2224,
            "thumb":"https://static.hummingbird.me/gallery_images/images/000/002/224/thumb/2224.jpeg?1375600048",
            "original":"https://static.hummingbird.me/gallery_images/images/000/002/224/original/2224.jpeg?1375600048"
          },
          {
            "id":2225,
            "thumb":"https://static.hummingbird.me/gallery_images/images/000/002/225/thumb/2225.jpeg?1375600049",
            "original":"https://static.hummingbird.me/gallery_images/images/000/002/225/original/2225.jpeg?1375600049"
          },
          {
            "id":2226,
            "thumb":"https://static.hummingbird.me/gallery_images/images/000/002/226/thumb/2226.jpeg?1375600050",
            "original":"https://static.hummingbird.me/gallery_images/images/000/002/226/original/2226.jpeg?1375600050"
          }
        ],
        "episodes":[
          {
            "id":169569,
            "title":"The Continuation Of The Dream",
            "synopsis":"After one year since the coup against Nagi, a powerful new enemy threatens to disturb the peace of the world. While Meister Arika is ready to protect the people, an argument between her and Queen Mashiro could prove to be her undoing.",
            "airdate":"2006-11-24",
            "number":1,
            "season_number":2
          },
          {
            "id":2451,
            "title":"Dream☆Wing ~ Dream's Whereabouts",
            "synopsis":"The Otomes face off against Nagi and the power of the Harmonium, and Arika comes into conflict with Nina in a final battle.",
            "airdate":"2006-03-30",
            "number":26,
            "season_number":1
          },
          {
            "id":2450,
            "title":"Maiden of the Azure Sky",
            "synopsis":"Nina operates the Harmonium in order to save the fatally wounded Sergay. Meanwhile, Operation Liberate Windbloom is put into full action, with rival armies squaring off against each other and the Otome attempting to gain their powers back.",
            "airdate":"2006-03-30",
            "number":25,
            "season_number":1
          },
          {
            "id":2449,
            "title":"For Your Sake...",
            "synopsis":"While plans to liberate Windbloom are underway, Sergay puts his life on the line in order to protect Nina and destroy the source of the power of the Slaves and Valkyries.",
            "airdate":"2006-03-23",
            "number":24,
            "season_number":1
          },
          {
            "id":2448,
            "title":"Arika of the Mysterious Valley",
            "synopsis":"Arika, Mashiro and Miyu end up in the Black Valley, where they encounter Mai and Mikoto the Cat Goddess. Natsuki and Nao later join them.",
            "airdate":"2006-03-16",
            "number":23,
            "season_number":1
          },
          {
            "id":2446,
            "title":"When The White Princess Awakens",
            "synopsis":"Nagi discovers that the true heir to the throne might be Nina. Mashiro feels compassion for her exiled people and asks Aswad to take them in. Sergay forms a plan to capture Mashiro with the assistance of the Valkyrie unit.",
            "airdate":"2006-03-02",
            "number":21,
            "season_number":1
          },
          {
            "id":2445,
            "title":"Don`t Call Me Nina",
            "synopsis":"Being distanced from her old friends, Nina is drawn ever more closer to her foster-father Sergay. Natsuki's request for aid is turned down in Aries, so she's forced to seek help from Cardair, where the king has just sent his Otome to claim valuable data from the village of Aswad.",
            "airdate":"2006-02-23",
            "number":20,
            "season_number":1
          },
          {
            "id":2443,
            "title":"Whiteout",
            "synopsis":"Three days after the disastrous raid against Windbloom, Mashiro is forced to join refugees fleeing from their homeland. Nina now serves as the Meister Otome of Nagi. Getting to know her exiled people better, Mashiro learns the painful truth about her leadership qualities.",
            "airdate":"2006-02-09",
            "number":18,
            "season_number":1
          },
          {
            "id":2442,
            "title":"Azure dance / Thoughts, When They Scatter",
            "synopsis":"With hostile forces invading the capital of Windbloom, all Otomes are summoned into battle. But an enemy plan causes the source of the Otome technology to shut down, and Arika finds herself fighting against her dearest friends.",
            "airdate":"2006-02-02",
            "number":17,
            "season_number":1
          },
          {
            "id":2441,
            "title":"It's a Promise",
            "synopsis":"Arika, Nina and Erstin all promise to become Meister Otomes together. However, Nagi de Artai and Schwarz begin a coup against Windbloom and Garderobe.",
            "airdate":"2006-01-26",
            "number":16,
            "season_number":1
          },
          {
            "id":2440,
            "title":"Arika, Crying",
            "synopsis":"Sergay rescues Arika from a dangerous situation, allowing her a chance to act on her feelings. Arika later talks to a similarly distressed Mashiro.",
            "airdate":"2006-01-19",
            "number":15,
            "season_number":1
          },
          {
            "id":2438,
            "title":"Towards the Red Sky",
            "synopsis":"Arika realizes she has developed feelings for Sergay, and is having hard time trying to choose between the fate of an Otome and finding a love of her life. Elsewhere, two Meister Otomes from different countries battle against each other.",
            "airdate":"2005-01-06",
            "number":13,
            "season_number":1
          },
          {
            "id":2437,
            "title":"Masquerade Ball?",
            "synopsis":"Arika impersonates the missing Mashiro and meets the foreign prince Takumi, while Nina pretends to be Arika. Meanwhile, Mashiro takes the name of Nina and ends up in the slums of Windbloom, where she meets a foreign boy.",
            "airdate":"2005-12-23",
            "number":12,
            "season_number":1
          },
          {
            "id":2435,
            "title":"That's the Great Crisis of a Maiden",
            "synopsis":"Garderobe, Aries, Windbloom, and Artai move to rescue the two lost Coral students, as a mysterious group, Aswad, moves through the area.",
            "airdate":"2005-12-09",
            "number":10,
            "season_number":1
          },
          {
            "id":2434,
            "title":"Ocean - Swimsuit + Distress = ?",
            "synopsis":"As a part of the Otome training, Arika's Coral class begins a 100 km hiking trip through Aries. With randomly chosen pairs, the students enter into wilderness, when things start to go wrong for Arika and her partner Erstin.",
            "airdate":"2005-12-02",
            "number":9,
            "season_number":1
          },
          {
            "id":2433,
            "title":"Destiny's Yoke",
            "synopsis":"After making a contract with Mashiro in the climax of the previous episode, Arika is now responsible for finding a way to cancel it. In order to learn more, Arika and her friends break into the Mausoleum of Garderobe.",
            "airdate":"2005-11-25",
            "number":8,
            "season_number":1
          },
          {
            "id":2432,
            "title":"The Blue Dance / Oath of the Maiden",
            "synopsis":"While helping with the reconstruction of the royal palace, Arika hears a rumor about Mashiro. Arika and Mashiro find a mysterious machine beneath the castle.",
            "airdate":"2005-11-18",
            "number":7,
            "season_number":1
          },
          {
            "id":2431,
            "title":"Nina, Gets Wrapped...orz",
            "synopsis":"Upperclassman Shiho's jealousy towards Nina reaches catastrophic heights, as she decides to sabotage the school's swimming pool during sports class. Once again, it's up to Arika and her friends to clean up the mess.",
            "airdate":"2005-11-11",
            "number":6,
            "season_number":1
          },
          {
            "id":2429,
            "title":"Blazing Transfer Student!!",
            "synopsis":"After a safe conclusion to the crisis of the previous episode, Arika is admitted to Garderobe and receives some important lessons on the way. She earns some new friends as well as some new enemies.",
            "airdate":"2005-10-27",
            "number":4,
            "season_number":1
          },
          {
            "id":2428,
            "title":"First Experience",
            "synopsis":"In order to acquire permission to attend the Otome school of Garderobe, unexperienced Arika must face Nina in an exhibition battle during the coronation ceremony of Mashiro. ",
            "airdate":"2005-10-20",
            "number":3,
            "season_number":1
          },
          {
            "id":2427,
            "title":"A Hurricane Blowing Through the Garden of Maidens!?",
            "synopsis":"Deeply impressed after witnessing the power of Meister Otome Shizuru Viola, Arika is more determined than ever to become a real Otome and attend the school of Garderobe. However, before her wish can become reality, certain conditions must be met.",
            "airdate":"2005-10-13",
            "number":2,
            "season_number":1
          },
          {
            "id":2426,
            "title":"Dreaming☆Arika",
            "synopsis":"Young Arika Yumemiya travels to the Kingdom of Windbloom in order to become an Otome. She soon becomes acquainted with Garderobe student Nina, Nina's foster father Sergay, and the rebellious princess Mashiro.",
            "airdate":"2005-10-06",
            "number":1,
            "season_number":1
          },
          {
            "id":2430,
            "title":"the Academy, the Uniform and Me",
            "synopsis":"Arika's studentship is put in jeopardy when her uniform appears in a store. To prove that she didn't sell the uniform, Arika places her trust in her new friends to uncover the true culprit.",
            "airdate":"2005-11-04",
            "number":5,
            "season_number":1
          },
          {
            "id":2436,
            "title":"Happy Birthday",
            "synopsis":"Nina and Arika are sent to escort Mashiro to a meeting with royal guests from a neighbouring country. To avoid an international conflict when Mashiro runs away, Windbloom comes up with a hasty substitute.",
            "airdate":"2005-12-16",
            "number":11,
            "season_number":1
          },
          {
            "id":2439,
            "title":"An Otome's S.O.S",
            "synopsis":"Arika's friends become concerned over her strange behavior. Meanwhile, Shizuru investigates the boiling Otome conflict between the two countries of Romulus and Remus.",
            "airdate":"2006-01-13",
            "number":14,
            "season_number":1
          },
          {
            "id":2444,
            "title":"17 Year-Old of Fate",
            "synopsis":"Mashiro wakes up in the village of Aswad and is reunited with Arika. Elsewhere, Natsuki and Nao make their way toward the Republic of Aries in order to request help.",
            "airdate":"2006-02-16",
            "number":19,
            "season_number":1
          },
          {
            "id":2447,
            "title":"The Song of Destruction",
            "synopsis":"Aswad attacks Cardair in retribution for their betrayal. Back at the village, Sergay and the Valkyries arrive to take Mashiro and Arika by force. Meanwhile, Nagi has Nina activate the true power of the Harmonium.",
            "airdate":"2006-03-09",
            "number":22,
            "season_number":1
          }
        ]
      }

    },

    {

      "id":100,
      "titles":{
        "canonical":"Fullmetal Alchemist",
        "english":"",
        "romaji":"Fullmetal Alchemist",
        "japanese":"鋼の錬金術師"
      },
      "slug":"fullmetal-alchemist",
      "synopsis":"The rules of alchemy state that to gain something, one must lose something of equal value. Alchemy is the process of taking apart and reconstructing an object into a different entity, with the rules of alchemy to govern this procedure. However, there exists an object that can bring any alchemist above these rules, the object known as the Philosopher's Stone. The young Edward Elric is a particularly talented alchemist who through an accident years back lost his younger brother Alphonse and one of his legs. Sacrificing one of his arms as well, he used alchemy to bind his brother's soul to a suit of armor. This lead to the beginning of their journey to restore their bodies, in search for the legendary Philosopher's Stone.",
      "startedAiringDate":"2003-10-04",
      "finishedAiringDate":"2004-10-02",
      "youtubeVideoId":"2Dsa8j_usqI",
      "ageRating":"PG13",
      "episodeCount":51,
      "episodeLength":25,
      "showType":"TV",
      "poster_image":"https://static.hummingbird.me/anime/poster_images/000/000/100/large/409464.jpg?1416242807",
      "cover_image":"https://static.hummingbird.me/anime/cover_images/000/000/100/thumb/681535-full_metal_alchemist_sgsd.jpg?1416242807",
      "communityRating":4.21197563549927,
      "genres":[
        "Action",
        "Adventure",
        "Comedy",
        "Drama",
        "Magic",
        "Fantasy",
        "Military"
      ],
      "producers":[
        "Bones",
        "Aniplex",
        "Square Enix",
        "FUNimation Entertainment",
        "Mainichi Broadcasting"
      ],
      "bayesian_rating":4.21197563549927,
      "links":{
        "gallery_images":[
          4687,
          4688,
          4689,
          4690
        ],
        "episodes":[
          3062,
          3061,
          3060,
          3059,
          3058,
          3057,
          3056,
          3055,
          3054,
          3053,
          3051,
          3050,
          3049,
          3047,
          3046,
          3045,
          3044,
          3043,
          3041,
          3040,
          3039,
          3038,
          3037,
          3035,
          3034,
          3033,
          3032,
          3030,
          3029,
          3028,
          3026,
          3025,
          3024,
          3023,
          3022,
          3020,
          3019,
          3018,
          3017,
          3063,
          3064,
          3065,
          3015,
          3016,
          3021,
          3027,
          3031,
          3036,
          3042,
          3048,
          3052
        ]

      },
      "linked":{
        "gallery_images":[
          {
            "id":4687,
            "thumb":"https://static.hummingbird.me/gallery_images/images/000/004/687/thumb/FMA-NCOP4-04.jpg?1378656000",
            "original":"https://static.hummingbird.me/gallery_images/images/000/004/687/original/FMA-NCOP4-04.jpg?1378656000"
          },
          {
            "id":4688,
            "thumb":"https://static.hummingbird.me/gallery_images/images/000/004/688/thumb/fullmetal_alchemist1.jpg?1378656013",
            "original":"https://static.hummingbird.me/gallery_images/images/000/004/688/original/fullmetal_alchemist1.jpg?1378656013"
          },
          {
            "id":4689,
            "thumb":"https://static.hummingbird.me/gallery_images/images/000/004/689/thumb/Roy_Mustang_using_flame_alchamy.jpg?1378656030",
            "original":"https://static.hummingbird.me/gallery_images/images/000/004/689/original/Roy_Mustang_using_flame_alchamy.jpg?1378656030"
          },
          {
            "id":4690,
            "thumb":"https://static.hummingbird.me/gallery_images/images/000/004/690/thumb/Scar.jpg?1378656057",
            "original":"https://static.hummingbird.me/gallery_images/images/000/004/690/original/Scar.jpg?1378656057"
          }
        ],
        "episodes":[
          {
            "id":3062,
            "title":"Farewell",
            "synopsis":"The army’s camp in the north, led by Armstrong and headed by Havoc posing as Mustang, starts a military coup. This distracts the attention of the Fuhrer and his minions, while the real Mustang plots his attack on the Fuhrer in Central. Al has been abducted by Envy. Sensei and Ed are forced to pursue the only lead they have on the Homunculi, the Fuhrer himself. They assault the headquarters to find the Fuhrer has gone home early, but they are fortunate in their timing, and are able to help the Tringham brothers (imposter Elrics) escape from prison there. Ed goes off to fight the Fuhrer while sensei battles with the new and improved Frank Archer.",
            "airdate":"2004-09-11",
            "number":48,
            "season_number":1
          },
          {
            "id":3061,
            "title":"Homunculus Sealed",
            "synopsis":"Ed discovers Sloth and Al at Tucker's research lab. He tries to convince Al that the Homunculus is not their mother, and not to be trusted, but Al is pretty sure that it'll be fine. So Ed has to do things his way. He reveals the pieces of his mother's corpse that he dug up in Rizenbul, stunning Sloth, and giving him a chance to seal her forever. Lust had drawn a sealing transmutation circle with her claws, so it could not be destroyed, and Ed lured Sloth into it. The deal was almost done, when Al in his infinite wisdom decided that he was going to save the homunculus, so he threw the box containing a piece of his mother out the window, thus freeing Sloth. Lust held her at bay while Ed went to get the box, but Wrath was waiting outside, and decided to absorb the box into himself so Ed couldn't get it back. More and more it became clear that the brothers would not win this battle so easily, especially with Al helping out the Homunculus. Sloth freed herself from the circle, and while Ed chased Sloth, who was now controlling Al's body from the inside, Wrath and Lust squared off. Lust is easily destroyed though, as Wrath found her locket and used it to stop her on the transmutation circle. Wrath killed Lust and headed off to find Sloth. Fortunately for the brothers, who were not in great shape at this point, Wrath was a bit inexperienced, and foolishly merged himself with Sloth. Having the box inside of him, this stopped them both and gave Ed his chance to destroy his creation, Sloth, once and for all.",
            "airdate":"2004-09-04",
            "number":47,
            "season_number":1
          },
          {
            "id":3060,
            "title":"Human Transmutation",
            "synopsis":"Al has gone to meet Tucker, and having seen Nina's lifeless body, agreed to help him so that Tucker will teach Al to use the Philosopher's Stone. Tucker is too eager to try to resurrect Nina, though, and Al is used as a component in the transmutation. Ed learns more of Dante's past as he and Sensei mill about her old mansion and talk about their experiences with her. Sensei discovered a love letter from Hohenheim to Dante which was dated nearly 400 years ago. Ed goes back to his room to find Al gone, and Lust and Wrath waiting for him there. An unlikely alliance occurs though, as Lust's mistrust of Dante leads her to cooperate with Ed in hopes that he will make her human again. They rush off to find Al at Tucker's old research lab. They are still on their way when Sloth arrives though, and tensions are high as the episode ends showing Al''s body corroded by Tucker's transmutation.",
            "airdate":"2004-08-28",
            "number":46,
            "season_number":1
          },
          {
            "id":3059,
            "title":"One Who Lets Her Heart Rot",
            "synopsis":"Everything begins to come together in this episode. Hohenheim has confronted Dante and the Homunculus, but he was taken by surprise when the Homunculus of his former wife appears, and he is captured by Dante and sent to the other side of the mysterious gate. Mustang confronts the military court, not about the Fuhrer yet, but about his secretary Juliet Douglass. His words revealed him to the Fuhrer, who orders him to the front lines on the north, to make it easy to dispose of him when the time comes. Ed and Al are on the run, disguising themselves. They don't make it very far before being discovered, but they were far enough for Ed to find Sensei, who knows of his plans when he asks for a piece of her child's corpse. Al, trusted to stay in hiding, of course runs off and meets with Tucker. He reveals Nina's motionless body to Al, who is taken by it, and offers to use himself to help her.",
            "airdate":"2004-08-21",
            "number":45,
            "season_number":1
          },
          {
            "id":3058,
            "title":"Hohenheim of Light",
            "synopsis":"Mustang has finally caught up to Ed and Al, but he explains that he is not there to apprehend them, but to find out what was really happening. They explain the Fuhrer''s identity, to the shock of everyone present. This prompts them to develop a plan for testing the truth of the claim. They arrive in Rizenbul to find Hohenheim and Al, despite his brother''s protests, spends as much time as he can with his long lost father. The homunculus are hiding out in the church that Ryla has taken Rose to, and it becomes apparent in a fabulous plot twist, that ''him'' that the homunculus are always talking about, is in fact Ryla, who is in fact Dante. The plot thickens as Hohenheim storms their sanctuary, having heard Dante''s name from the brothers. His skill in alchemy is incredible, even enough so to scare Gluttony. Ryla faces off with him using her own alchemy, and action ensues.",
            "airdate":"2004-08-07",
            "number":44,
            "season_number":1
          },
          {
            "id":3057,
            "title":"The Stray Dog",
            "synopsis":"Now that Al has actually become the Philosopher's Stone, the brothers are forced to flee, knowing well that the Homunculus would soon be after them. The Fuhrer, in turn, places orders for the brothers to be captured. Mustang takes this responsibility, knowing that if they are captured by anyone else, they would surely not get the opportunity to explain themselves. Winry and Seska have gone to Rizenbul to lay low for a while after their scare at the military base. To their surprise, the mysterious stranger on the train turned out to be none other than Hoenheim, the Elric's father. They receive word of the hunt of Ed and Al, and the two go to try to help. Ed and Al, for unclear reasons, are hurrying back to their hometown, knowing that the military would surely be there, but they are not dissuaded, and trouble brews as Mustang's soldiers get closer and closer.",
            "airdate":"2004-07-31",
            "number":43,
            "season_number":1
          },
          {
            "id":3056,
            "title":"His Name is Unknown",
            "synopsis":"Al’s body has been transmuted into a bomb by Kimblee just before his death, and Scar, in a fit of compassion, prepares Al to be saved by turning him into the Philosopher’s Stone. Scar sacrifices his right arm, melding it with Al, and making Al a container waiting for the army’s invasion of the city. Ed manages to fight off the two homunculi and with Ryla and Rose, they escape, but Ed knows he has to go back, and returns to the city. Mustang, using information gathered by Armstrong in his mock service of Archer, goes down to Tucker’s research room to put a stop to the madness. They are beset by chimeras and though they have little trouble fighting them off, Archer gets away. Before anyone can stop him, he sends the army into Lior. There Scar sacrifices himself to transmute the army into Al’s body, making him the Philosopher’s Stone.",
            "airdate":"2004-07-24",
            "number":42,
            "season_number":1
          },
          {
            "id":3055,
            "title":"Holy Mother",
            "synopsis":"At the brink of another civil war, Scar justifies his intentions for Lior to Ed. However, after learning from Lyra what Scar intends to do to the Military, he sends an urgent message to Mustang to stay out of the city. Meanwhile, back at Regional Headquarters, Al ponders Martel's warning as the Military gets ready for battle.",
            "airdate":"2004-07-24",
            "number":41,
            "season_number":1
          },
          {
            "id":3054,
            "title":"The Scar",
            "synopsis":"Ed confronts Scar in Lior and learns his objective for being in the city. When arguing against the plan doesn't work, Ed finds himself battling Scar yet again. In the midst of the battle, though, they are interrupted by Lust and Gluttony. After stopping them, they are again interrupted by two more people from Ed's past who persuade Ed to listen to Scar's story.\n\nMeanwhile, Marta and Al are captured by Kimbley and taken back to Regional Headquarters.",
            "airdate":"2004-07-17",
            "number":40,
            "season_number":1
          },
          {
            "id":3053,
            "title":"Secret of Ishbal",
            "synopsis":"As Ed, Al and Marta continue their journey to Ishbal, they discuss the history and events that led up to the Isbalan Civil War. Through Marta, the brothers learn the truth behind the conflict. Meanwhile, Archer arrives at Lior to quell the new civil war.",
            "airdate":"2004-07-10",
            "number":39,
            "season_number":1
          },
          {
            "id":3051,
            "title":"The Flame Alchemist, The Bachelor Lieutenant, \u0026 The Mystery of Warehouse 13",
            "synopsis":"Under orders from Internal Affairs to investigate Colonel Mustang, Second Lieutenant Havoc compiles a journal on the Colonel's activities. As he, Breda, Falman and Fuery follow Mustang and discover more about him then they expected. Meanwhile, Sciezka finds an opportunity to further her own investigation into Hughes death.",
            "airdate":"2004-06-26",
            "number":37,
            "season_number":1
          },
          {
            "id":3050,
            "title":"The Sinner Within Me",
            "synopsis":"Ed, Al, and Winry continue on their travel to Ishbal in hopes of finding out how they made the fabled philosopherâ€™s stone without alchemy.  Along the way the trio uncovers more and more about the truth of the Ishbal war.  But are Ed and Al ready to hear it?   Meanwhile Winry receives a startling revelation about the death of her parents.",
            "airdate":"2004-06-19",
            "number":36,
            "season_number":1
          },
          {
            "id":3049,
            "title":"Reunion of the Fallen",
            "synopsis":"In the aftermath of the battle between Ed and Greed, Lust finds herself pondering the meaning of life: Where did she come from? And where will she go when she dies? As she questions herself, she is surprised to see a familiar face; a man whom she met two years earlier. She had helped him use alchemy to save a town that was plagued by a strange illness.  Meanwhile, Ed and company search for an Ishbalan outpost in hopes of finding the more information about the Philosopher's Stone.",
            "airdate":"2004-06-12",
            "number":35,
            "season_number":1
          },
          {
            "id":3047,
            "title":"Al, Captured",
            "synopsis":"Al is kidnapped by Greed and his gang, leaving Ed frantically searching for him. Izumi and Sigu join him in the search, and in the process Izumi discovers how much her young proteges have learned on their own.",
            "airdate":"2004-05-29",
            "number":33,
            "season_number":1
          },
          {
            "id":3046,
            "title":"Dante of the Deep Wood",
            "synopsis":"After battling Wrath, Izumi decides it's time Ed and Al meets with her old Master, Dante: the alchemist who taught her everything she knows, and who also knew Ed and Al's father, Hohenheim Elric. However, they are delayed yet again as Greed decides to take on the Elric Brothers.",
            "airdate":"2004-05-15",
            "number":32,
            "season_number":1
          },
          {
            "id":3045,
            "title":"Sin",
            "synopsis":"Izumi has successfully escaped with the boy, but to no avail--Envy had already told the child the truth about himself, and his personality is changing. With all her secrets exposed, Izumi makes plans to atone for her sins. Ed and Al rush to intervene before it's too late.",
            "airdate":"2004-05-08",
            "number":31,
            "season_number":1
          },
          {
            "id":3044,
            "title":"Assault on South Headquarters",
            "synopsis":"Under suspicion of being a Homunculus conspirator, the strange wild boy from Yock is taken into custody by Lt. Col. Archer. Enraged by the arrest, Izumi takes on the military with the full force of her alchemic powers. Meanwhile, Scar has been spotted in the Southern Slums by a retired military officer.",
            "airdate":"2004-05-01",
            "number":30,
            "season_number":1
          },
          {
            "id":3043,
            "title":"The Untainted Child",
            "synopsis":"As Ed and Al get ready to leave Yock Island, a strange boy wanders out of the forest. Suffering from amnesia, he can't remember how he got there. Amazingly, he is a gifted alchemist; not only can he perform alchemy, but, like Ed, he doesn't need a transmutation circle. He also has other amazing abilities, compelling Izumi to take the child in. Ed, however,  is suspicious of the boy's sudden appearance, and doesn't believe he is as innocent as he appears to be.",
            "airdate":"2004-04-24",
            "number":29,
            "season_number":1
          },
          {
            "id":3041,
            "title":"Teacher",
            "synopsis":"Izumi: a skillful alchemist with an iron will and the Elric Brother's childhood teacher. She is the one person Ed and Al have both tried to avoid. Now, their past has caught up with them when she finds them at Rush Valley. Enraged to find out what they have become, she takes them back to her hometown for an interrogation they won't soon forget.",
            "airdate":"2004-04-10",
            "number":27,
            "season_number":1
          },
          {
            "id":3040,
            "title":"Her Reason",
            "synopsis":"Rush Valley: the Automail capital of the world. Here the sight of metal limbs is commonplace. While browsing through the village, Ed, Al and Winry meet a tough girl named Paninya. With her right arm and legs made of a special automail, she can hold her own in a fight. Watching her, Winry asks herself a tough question: Why stick with Ed if he doesn't seem to care about her?",
            "airdate":"2004-04-03",
            "number":26,
            "season_number":1
          },
          {
            "id":3039,
            "title":"Words of Farewell",
            "synopsis":"Ed announces that he and Al have decided to stop searching for the Philosopher's Stone. Boarding a train headed South, they leave Central to visit their old teacher. Meanwhile, Hughes has discovered the truth behind the Fifth Laboratory -- a truth that could topple the Government.",
            "airdate":"2004-03-27",
            "number":25,
            "season_number":1
          },
          {
            "id":3038,
            "title":"Bonding Memories",
            "synopsis":"Still troubled by the uncertainty of his own existence, Alphonse runs away from Ed and Winry. Searching for the truth, he meets two boys who lead him to the hidden Ishbalen Encampment, where survivors of the Ishbal Massacre have been living. There, he finds a recovering Scar. Unfortunately, mercenaries arrive and slaughter many of the Ishbalens. Al finds himself helping Scar as they defend the Encampment and the rest of the survivors.",
            "airdate":"2004-03-20",
            "number":24,
            "season_number":1
          },
          {
            "id":3037,
            "title":"Heart of Steel",
            "synopsis":"Ed and Al once again find themselves in a hospital. Winry has traveled to Central to make repairs, but is angered when the brothers don't want to share their experiences with her. As they recuperate, Al ponders his existence and hammers Ed with questions. Who is he? Is his memories of being human real, or is it all a lie?",
            "airdate":"2004-03-13",
            "number":23,
            "season_number":1
          },
          {
            "id":3035,
            "title":"The Red Glow",
            "synopsis":"The battle between Al and Number 66 is interrupted by the appearance of Scar. As the three square off, Number 66 triggers an explosion that causes yet more trouble for everyone involved. Meanwhile, Ed and Number 48 have reached the deepest parts of the Fifth Laboratory. Now, Ed faces a bigger threat as his morality is challenged by someone from his past; will he give in to the temptation of the power he has been seeking, or will he hold true to his beliefs?",
            "airdate":"2004-02-28",
            "number":21,
            "season_number":1
          },
          {
            "id":3034,
            "title":"Soul of the Guardian",
            "synopsis":"The battle in the Fifth Laboratory continues. Separated, each now face a formidable foe: Ed faces off with Number 48 while Al deals with the insane Number 66. After an exchange with Number 66, though, Al soon comes to question his very existence.",
            "airdate":"2004-02-21",
            "number":20,
            "season_number":1
          },
          {
            "id":3033,
            "title":"The Truth Behind Truths",
            "synopsis":"Ed and Al have spent years searching for the Philosopher's Stone. After studying Marco's notes, however, Al learns of the grusome secret behind its power. With this knowledge, can the Elric Brothers continue with their quest to be whole again?",
            "airdate":"2004-02-14",
            "number":19,
            "season_number":1
          },
          {
            "id":3032,
            "title":"Marcoh's Notes",
            "synopsis":"Ed is overjoyed to be back in Central, and can't wait to begin his search for Marcoh's notes in the Central Library's First Branch. Unfortunately, they learn that the First Branch was completely destroyed the night before. To add to their troubles, witnesses had seen Scar in the vicinity before the fire. Now, coupled with a new pair of guards (one of whom is insistent that they go somewhere safer) the brothers must find another way to discover the secret of the Philosopher's Stone.",
            "airdate":"2004-02-07",
            "number":18,
            "season_number":1
          },
          {
            "id":3030,
            "title":"That Which Is Lost",
            "synopsis":"Injured from their latest battle, the brothers decide to return to Risembool for repairs. They make plans to meet up with Winry, but along the way a mixup occurs and Al vanishes from one of the train stations. Now Al must make a detour to find him, and in the process question his deepest held belief.",
            "airdate":"2004-01-24",
            "number":16,
            "season_number":1
          },
          {
            "id":3029,
            "title":"The Ishbal Massacre",
            "synopsis":"As the Strong Arm Alchemist holds off Scar, he realizes that the killer is from Ishbal. Meanwhile, as Ed, Al and Doctor Marcoh make their way through the village, the Doctor recounts the Ishbal â€˜Rebellion' and how he and the other State Alchemist were ordered to level the entire city using the Red Stones. The events still haunt Marcoh, Mustang and Armstrong to this day.",
            "airdate":"2004-01-17",
            "number":15,
            "season_number":1
          },
          {
            "id":3028,
            "title":"Destruction's Right Hand",
            "synopsis":"Following Mustang's tip, Ed and Al travel to Marcoh's village to ask him about the Philosopher's Stone. However, before they are able to learn more about the Stone from the Doctor, they are interrupted by the arrival of Brigadier Basque Gran, who arrests the doctor for desertion during the Eastern War. Ed chases after the General's car, determined not to let the General get away with another cover up. They are all surprised, however,  when the car is stopped by the sudden appearance of the Scarred Man.",
            "airdate":"2004-01-10",
            "number":14,
            "season_number":1
          },
          {
            "id":3026,
            "title":"The Other Brothers Elric (2)",
            "synopsis":"When Ed and Al discover the source of the strange disease that has stricken Xenotime, they also find out the history behind the Tringham Brother's father and his involvement with the red water. This news does not please Mugear, who had commissioned the Tringham Brothers to make the Philosopher's Stone. He is determined to stop the real Elric Brothers from interfering with his work, and with the red water he may be able to do it.",
            "airdate":"2003-12-20",
            "number":12,
            "season_number":1
          },
          {
            "id":3025,
            "title":"The Other Brothers Elric (1)",
            "synopsis":"Following a rumor, Ed and Al travel to Xenotime where they hope to find the two alchemists who are creating the Philosopher's Stone. When they reach the village, however, they discover two boys masquerading as the Elric Brothers. Who are these boys, and what is this â€˜red water' they are creating?",
            "airdate":"2003-12-13",
            "number":11,
            "season_number":1
          },
          {
            "id":3024,
            "title":"The Phantom Thief",
            "synopsis":"Not wanting to face Mustang with the news that they still have no new information about the Philosopher's Stone, Ed and Al decide to vist a small tourist town on the pretext of learning more about the Stone. While there, they become involved in the manhunt for a \"\"Robin Hood\"\"-like theif. While Ed has his doubts about her true agenda, does she have any real knowledge about the Stone's whereabouts?",
            "airdate":"2003-12-06",
            "number":10,
            "season_number":1
          },
          {
            "id":3023,
            "title":"Be Thou for the People",
            "synopsis":"Mustang sends Ed on assignment to inspect a small mining colony out in the East provinces. Once there, he and Al are mistaken as tourists and are greeted by the local hotel owner. However, once they learn Ed is a State Alchemist, he is attacked and tossed out of the hotel. Ed quickly learns why; the town is being taxed beyond its limits by a crooked lieutenant and his Alchemist. Will Ed do what is right and save the colony from this tyrant?",
            "airdate":"2003-11-29",
            "number":9,
            "season_number":1
          },
          {
            "id":3022,
            "title":"The Philosopher's Stone",
            "synopsis":"Angered over the events at the Tuckers, Edward resigns his position as a State Alchemist. Meanwhile, Winry arrives in the city for a visit and to congratulate Ed on his commission. With a serial killer still on the loose, and Hughes still at a lost for viable leads, will Winry become his next victim?",
            "airdate":"2003-11-22",
            "number":8,
            "season_number":1
          },
          {
            "id":3020,
            "title":"The Alchemy Exam",
            "synopsis":"Ed and Al finally arrive in Central City. Needing a place to stay, Mustang places the boys under the supervision of 'Sewing-Life Alchemist' Shou Tucker. Awed by Tucker's mansion and vast library of Alchemy books, the brothers spend some happy days of study and good fun. All their work leads up to one event -- the Alchemy Exam.",
            "airdate":"2003-11-08",
            "number":6,
            "season_number":1
          },
          {
            "id":3019,
            "title":"The Man with the Mechanical Arm",
            "synopsis":"When the train the brothers are taking to Central is hijacked by terrorists, Ed and Al learn that General Haruko and his family are also on board. Bald, the leader of the terrorists, threatens to kill the hostages unless their demands are met. Ed meets the challenge to free everyone, but he soon discovers that he and Bald have something in common; an Automail arm.",
            "airdate":"2003-11-01",
            "number":5,
            "season_number":1
          },
          {
            "id":3018,
            "title":"A Forger's Love",
            "synopsis":"While traveling to Central City to become State Alchemist, Ed and Al learn of a man named Majihal living in a nearby town. They remember that this Alchemist had written to their father about ways to create human life. Eager to learn more, Ed and Al travel to the town to meet Majihal. When they get there, however, they learn that the town is being hunted by a ghostly woman who is taking the souls of young girls. Majihal banishes the ghost, but is he hiding something more sinister?",
            "airdate":"2003-10-25",
            "number":4,
            "season_number":1
          },
          {
            "id":3017,
            "title":"Mother",
            "synopsis":"Ed and Al grew up at a time when their country was at war. While news from the front devastated friends and neighbors, their mother was a pillar of strength. She made them feel safe in an uncertain time, and encouraged their gifts of Alchemy. When she died, Ed and Al were determined to bring her back. They believed they had nothing left to loose, but they didn't know how wrong they were.",
            "airdate":"2003-10-18",
            "number":3,
            "season_number":1
          },
          {
            "id":3063,
            "title":"Beyond the Gate",
            "synopsis":"Alphsone has been captured and Roy is about to begin the last part of his plan. Edward, meanwhile is traveling deeper and deeper under Central City to find the one who controls the homunculi and ultimatly, his brother.",
            "airdate":"2004-09-18",
            "number":49,
            "season_number":1
          },
          {
            "id":3064,
            "title":"Death",
            "synopsis":"Ed now joins his father on the other side of the gate which seems to be a lot like our world but what will Ed do there and how will he get back to the world he knows?",
            "airdate":"2004-09-25",
            "number":50,
            "season_number":1
          },
          {
            "id":3065,
            "title":"Laws and Promises",
            "synopsis":"It is the very end, what will become of Ed and Al? Will the dark plot of the villians finally be realized? It all comes down to the wire in the final instalment of the FMA anime.",
            "airdate":"2004-10-02",
            "number":51,
            "season_number":1
          },
          {
            "id":3015,
            "title":"To Challenge the Sun",
            "synopsis":"On their hunt for the Philosopher's Stone, Ed and Al come upon a strangely prosperous city in the middle of the desert. They find that the leader is a priest who preforms miracles and resurrections, much to the delight of the citizens. Are these Acts of God, as Father Cornello claims, or is he using the power of the Philosopher's Stone to boost his Alchemy? Ed and Al investigate to find the truth.",
            "airdate":"2003-10-04",
            "number":1,
            "season_number":1
          },
          {
            "id":3016,
            "title":"Body of the Sanctioned",
            "synopsis":"Ed and Al discover the truth behind Father Cornello's miracles. However, if they reveal this truth, they will destroy the hopes and dreams of Rose and the other citizens of the city. Would it be more compassionate to withhold the truth and allow them to continue to live comfortably in the deception? First, though, they must survive Father Cornello's Alchemy before they can make that decision.",
            "airdate":"2003-10-11",
            "number":2,
            "season_number":1
          },
          {
            "id":3021,
            "title":"Night of the Chimera's Cry",
            "synopsis":"Now that Edward is an official State Alchemist, the brothers can begin their quest to restore their bodies. However, something about Tucker's chimera experiments begin to trouble Ed. As he investigates, Ed begins to see a sinister side to alchemy. Now he and Al must stop a friend from committing the unthinkable.",
            "airdate":"2003-11-15",
            "number":7,
            "season_number":1
          },
          {
            "id":3027,
            "title":"Fullmetal vs. Flame",
            "synopsis":"The annual Alchemy Assessment is fast approaching, and Ed still has not have much success with his search for the Philosopher's Stone. Tired of Mustang's constant rebuke, and with nothing substantial to show for his Assessment, Ed comes up with a solution to settle both problems: Challenge the Colonel to a duel -- The Flame Alchemist against the Fullmetal Alchemist.",
            "airdate":"2003-12-27",
            "number":13,
            "season_number":1
          },
          {
            "id":3031,
            "title":"House of the Waiting Family",
            "synopsis":"The Elrics finally reach their childhood hometown of Risembool. As the brothers bide their time while Winry and her grandmother make their repairs, they sort through the ashes of their old home. But as Ed makes a visit to their mother's grave, Al comes to a disturbing realization about himself.",
            "airdate":"2004-01-31",
            "number":17,
            "season_number":1
          },
          {
            "id":3036,
            "title":"Created Human",
            "synopsis":"Ed reaches the heart of the Fifth Laboratory and learns that it was used as a biological research facility for the military. There, he is nearly fooled into doing an unspeakable act. He manages to escape, but then discovers that Envy has taken Al hostage. Meanwhile, Hughes has ordered Armstrong and the others to find out what is going on in the Laboratory.",
            "airdate":"2004-03-06",
            "number":22,
            "season_number":1
          },
          {
            "id":3042,
            "title":"One is All, All is One",
            "synopsis":"Izumi sends the brothers to the savage Island of Yock. Ed and Al remember when, as children, when they were dumped on the very same island. With no food or shelter, they are left with only the cryptic words of Izumi: \"\"One is All, All is One.\"\" Now, they must relearn the lessons of the past to survive their latest test.",
            "airdate":"2004-04-17",
            "number":28,
            "season_number":1
          },
          {
            "id":3048,
            "title":"Theory of Avarice",
            "synopsis":"The battle to rescue Al is in full force, as Major Armstrong takes to the sewers to fight off Greed's men while Ed remains on the surface in a showdown with Greed and the Military. As Ed and Greed face off, Ed must make the ultimate decision: is he willing to take someone's life to save his brother?",
            "airdate":"2004-06-05",
            "number":34,
            "season_number":1
          },
          {
            "id":3052,
            "title":"With the River's Flow",
            "synopsis":"After crashing the vehicle Winry left them, Ed and Al are forced to continue their journey to Ishbal on foot. The brother's tempers flare and they end up separated in a small town. Meanwhile, back at Central, Winry and Sciezka join forces to find the cause behind Hughes death.",
            "airdate":"2004-07-03",
            "number":38,
            "season_number":1
          }
        ]
      }
    },
    {
      "id":601,
      "titles":{
        "canonical":"Galaxy Angel 3",
        "english":null,
        "romaji":"Galaxy Angel 3",
        "japanese":"ギャラクシーエンジェル"
      },
      "slug":"galaxy-angel-3",
      "synopsis":"Their mission is to locate and retrieve the mysterious Lost Technology, even though these girls have never been known to stick to their mission. Milfeulle, Forte, Ranpha, Mint, and Vanilla are back for another round of hilarity. The Galaxy Angels have sworn to protect the galaxy one planet at a time. Now with some competition with the newly-formed Twin Star Brigade, they continue to find themselves in the center of chaos... though usually they're the ones at fault for its cause.\n(Source: ANN)",
      "startedAiringDate":"2002-10-06",
      "finishedAiringDate":"2003-03-31",
      "youtubeVideoId":null,
      "ageRating":"PG",
      "episodeCount":26,
      "episodeLength":24,
      "showType":"TV",
      "poster_image":"https://static.hummingbird.me/anime/poster_images/000/000/601/large/601.jpg?1408441868",
      "cover_image":"/cover_images/thumb/missing.png",
      "communityRating":3.65543676507508,
      "genres":[
        "Comedy",
        "Sci-Fi"
      ],
      "producers":[
        "Madhouse Studios",
        "Bandai Visual",
        "Broccoli",
        "Yomiko Advertising",
        "Bandai Entertainment",
        "Studio Jack"
      ],
      "bayesian_rating":3.65543676507508,
      "links":{
        "gallery_images":[

        ],
        "episodes":[
          161345,
          161329,
          161261,
          161237,
          161200,
          161129,
          161103,
          161064,
          161022,
          161388,
          161355,
          161337,
          161316,
          161306,
          161287,
          161249,
          161224,
          161214,
          161182,
          161165,
          161146,
          161118,
          161089,
          161077,
          161060,
          161039,
          13529,
          13530,
          13531,
          13532,
          13533,
          13534,
          13535,
          13536,
          13537,
          13538,
          13539,
          13540,
          13541,
          13542,
          13543,
          13544,
          13545,
          13546,
          13547,
          13548,
          13549,
          13550,
          13551,
          13552,
          13553,
          13554
        ]

      },
      "linked":{
        "gallery_images":[

        ],
        "episodes":[
          {
            "id":161345,
            "title":"Anyway Angel Morning Set / Starchild Sausage",
            "synopsis":null,
            "airdate":null,
            "number":24,
            "season_number":3
          },
          {
            "id":161329,
            "title":"Retry Rice / An Offering That you Must Not Eat",
            "synopsis":null,
            "airdate":null,
            "number":22,
            "season_number":3
          },
          {
            "id":161261,
            "title":"Competitive Sake Full of Predictions / Jiggly Pudding",
            "synopsis":null,
            "airdate":null,
            "number":18,
            "season_number":3
          },
          {
            "id":161237,
            "title":"The Sign is Boullion / Acting Angel Seafood Soup",
            "synopsis":null,
            "airdate":null,
            "number":16,
            "season_number":3
          },
          {
            "id":161200,
            "title":"Junk Ramen, Extra Noodles Available / Continuously Pushed Sweet Red Bean Soup",
            "synopsis":null,
            "airdate":null,
            "number":13,
            "season_number":3
          },
          {
            "id":161129,
            "title":"Pour, Ambitious, Chilled Seasame Chicken / A String of Handmade Noodles with No Connection",
            "synopsis":null,
            "airdate":null,
            "number":9,
            "season_number":3
          },
          {
            "id":161103,
            "title":"Ultra Rare Fortune Cookie / Ultra Hot Kid's Meal",
            "synopsis":null,
            "airdate":null,
            "number":7,
            "season_number":3
          },
          {
            "id":161064,
            "title":"Chilled Mackerel with a Prize / Slippery Pasta ",
            "synopsis":null,
            "airdate":null,
            "number":4,
            "season_number":3
          },
          {
            "id":161022,
            "title":"Old Man Detective Novel Rice Porridge / Original Angel Parfait with the Works ",
            "synopsis":null,
            "airdate":null,
            "number":1,
            "season_number":3
          },
          {
            "id":161388,
            "title":"Final Dish REBECCA (Part 1) / Final Dish REBECCA (Part 2)",
            "synopsis":null,
            "airdate":null,
            "number":26,
            "season_number":3
          },
          {
            "id":161355,
            "title":"Fresh Packed Fish / Prohibited String-pulling Fermented Beans",
            "synopsis":null,
            "airdate":null,
            "number":25,
            "season_number":3
          },
          {
            "id":161337,
            "title":"Sneaky Seaweed Wrap / Fish Jerky Over a Thousand Nights",
            "synopsis":null,
            "airdate":null,
            "number":23,
            "season_number":3
          },
          {
            "id":161316,
            "title":"Magic Pot Roast / Sub-zero Hot Hot Dog",
            "synopsis":null,
            "airdate":null,
            "number":21,
            "season_number":3
          },
          {
            "id":161306,
            "title":"Premium Tuna that just won't Slip / Assort Rock Candy Delivery",
            "synopsis":null,
            "airdate":null,
            "number":20,
            "season_number":3
          },
          {
            "id":161287,
            "title":"Deep Fried Love Letter / Galatic Rose Tea",
            "synopsis":null,
            "airdate":null,
            "number":19,
            "season_number":3
          },
          {
            "id":161249,
            "title":"Specialty Rocky Fish Cake / Broken-up Hot Spring Rice Cracker",
            "synopsis":null,
            "airdate":null,
            "number":17,
            "season_number":3
          },
          {
            "id":161224,
            "title":"Sun-dried Pork Feet / Top Imitation Goose",
            "synopsis":null,
            "airdate":null,
            "number":15,
            "season_number":3
          },
          {
            "id":161214,
            "title":"Ryuru-riku Magical Steak / Baum-kuhen to Worry Over",
            "synopsis":null,
            "airdate":null,
            "number":14,
            "season_number":3
          },
          {
            "id":161182,
            "title":"All-You-Can-Eat Juicy Yakiniku / Glitter Clunk Mixed Juice",
            "synopsis":null,
            "airdate":null,
            "number":12,
            "season_number":3
          },
          {
            "id":161165,
            "title":"Frequently Ordered Sushi / Fortuitous Family Set",
            "synopsis":null,
            "airdate":null,
            "number":11,
            "season_number":3
          },
          {
            "id":161146,
            "title":"Bamboo-Cutter Platter / Wandering Cat Food",
            "synopsis":null,
            "airdate":null,
            "number":10,
            "season_number":3
          },
          {
            "id":161118,
            "title":"Good-bye to our Steaming Teapot / Mustached Beef Rib Rice Bowl with Rich Sauce",
            "synopsis":null,
            "airdate":null,
            "number":8,
            "season_number":3
          },
          {
            "id":161089,
            "title":"Painful Walnut Pie / Angel Hotchpotch Taste Test",
            "synopsis":null,
            "airdate":null,
            "number":6,
            "season_number":3
          },
          {
            "id":161077,
            "title":"Overpriced Salad Bar / Angel Banana Discount",
            "synopsis":null,
            "airdate":null,
            "number":5,
            "season_number":3
          },
          {
            "id":161060,
            "title":"Deluxe Milfeulle Surprise Sandwich / Hug Hug Fish Pot",
            "synopsis":null,
            "airdate":null,
            "number":3,
            "season_number":3
          },
          {
            "id":161039,
            "title":"Shuffle French without Dessert / Special Appetizer without Main Dish",
            "synopsis":null,
            "airdate":null,
            "number":2,
            "season_number":3
          },
          {
            "id":13529,
            "title":"Episode 1",
            "synopsis":null,
            "airdate":null,
            "number":1,
            "season_number":null
          },
          {
            "id":13530,
            "title":"Episode 2",
            "synopsis":null,
            "airdate":null,
            "number":2,
            "season_number":null
          },
          {
            "id":13531,
            "title":"Episode 3",
            "synopsis":null,
            "airdate":null,
            "number":3,
            "season_number":null
          },
          {
            "id":13532,
            "title":"Episode 4",
            "synopsis":null,
            "airdate":null,
            "number":4,
            "season_number":null
          },
          {
            "id":13533,
            "title":"Episode 5",
            "synopsis":null,
            "airdate":null,
            "number":5,
            "season_number":null
          },
          {
            "id":13534,
            "title":"Episode 6",
            "synopsis":null,
            "airdate":null,
            "number":6,
            "season_number":null
          },
          {
            "id":13535,
            "title":"Episode 7",
            "synopsis":null,
            "airdate":null,
            "number":7,
            "season_number":null
          },
          {
            "id":13536,
            "title":"Episode 8",
            "synopsis":null,
            "airdate":null,
            "number":8,
            "season_number":null
          },
          {
            "id":13537,
            "title":"Episode 9",
            "synopsis":null,
            "airdate":null,
            "number":9,
            "season_number":null
          },
          {
            "id":13538,
            "title":"Episode 10",
            "synopsis":null,
            "airdate":null,
            "number":10,
            "season_number":null
          },
          {
            "id":13539,
            "title":"Episode 11",
            "synopsis":null,
            "airdate":null,
            "number":11,
            "season_number":null
          },
          {
            "id":13540,
            "title":"Episode 12",
            "synopsis":null,
            "airdate":null,
            "number":12,
            "season_number":null
          },
          {
            "id":13541,
            "title":"Episode 13",
            "synopsis":null,
            "airdate":null,
            "number":13,
            "season_number":null
          },
          {
            "id":13542,
            "title":"Episode 14",
            "synopsis":null,
            "airdate":null,
            "number":14,
            "season_number":null
          },
          {
            "id":13543,
            "title":"Episode 15",
            "synopsis":null,
            "airdate":null,
            "number":15,
            "season_number":null
          },
          {
            "id":13544,
            "title":"Episode 16",
            "synopsis":null,
            "airdate":null,
            "number":16,
            "season_number":null
          },
          {
            "id":13545,
            "title":"Episode 17",
            "synopsis":null,
            "airdate":null,
            "number":17,
            "season_number":null
          },
          {
            "id":13546,
            "title":"Episode 18",
            "synopsis":null,
            "airdate":null,
            "number":18,
            "season_number":null
          },
          {
            "id":13547,
            "title":"Episode 19",
            "synopsis":null,
            "airdate":null,
            "number":19,
            "season_number":null
          },
          {
            "id":13548,
            "title":"Episode 20",
            "synopsis":null,
            "airdate":null,
            "number":20,
            "season_number":null
          },
          {
            "id":13549,
            "title":"Episode 21",
            "synopsis":null,
            "airdate":null,
            "number":21,
            "season_number":null
          },
          {
            "id":13550,
            "title":"Episode 22",
            "synopsis":null,
            "airdate":null,
            "number":22,
            "season_number":null
          },
          {
            "id":13551,
            "title":"Episode 23",
            "synopsis":null,
            "airdate":null,
            "number":23,
            "season_number":null
          },
          {
            "id":13552,
            "title":"Episode 24",
            "synopsis":null,
            "airdate":null,
            "number":24,
            "season_number":null
          },
          {
            "id":13553,
            "title":"Episode 25",
            "synopsis":null,
            "airdate":null,
            "number":25,
            "season_number":null
          },
          {
            "id":13554,
            "title":"Episode 26",
            "synopsis":null,
            "airdate":null,
            "number":26,
            "season_number":null
          }
        ]
      }
    },
    {
      "id":609,
      "titles":{
        "canonical":"Fullmetal Alchemist: Reflections",
        "english":null,
        "romaji":"Fullmetal Alchemist: Reflections",
        "japanese":null
      },
      "slug":"fullmetal-alchemist-reflections",
      "synopsis":"A reflection on what happened during the FMA TV series.",
      "startedAiringDate":"2005-03-19",
      "finishedAiringDate":null,
      "youtubeVideoId":null,
      "ageRating":"PG13",
      "episodeCount":1,
      "episodeLength":55,
      "showType":"Special",
      "poster_image":"https://static.hummingbird.me/anime/poster_images/000/000/609/large/609.jpg?1408441884",
      "cover_image":"/cover_images/thumb/missing.png",
      "communityRating":3.69896178306195,
      "genres":[
        "Adventure",
        "Comedy",
        "Drama",
        "Fantasy",
        "Military"
      ],
      "producers":[
        "Bones"
      ],
      "bayesian_rating":3.69896178306195,
      "links":{
        "gallery_images":[

        ],
        "episodes":[
          13612
        ]

      },
      "linked":{
        "gallery_images":[

        ],
        "episodes":[
          {
            "id":13612,
            "title":"Episode 1",
            "synopsis":null,
            "airdate":null,
            "number":1,
            "season_number":null
          }
        ]
      }
    },
    {
      "id":699,
      "titles":{
        "canonical":"Gasaraki",
        "english":null,
        "romaji":"Gasaraki",
        "japanese":"ガサラキ"
      },
      "slug":"gasaraki",
      "synopsis":"The flames of war explode in the Middle East as two shadow forces unleash monstrous new weapons of mass destruction! But in a world in which giant robots are real, the most dangerous weapon of all lies buried within a human mind. Yushiro, the fourth son of the mysterious and powerful Gowa family, finds himself at the center of events that will change the future of mankind forever! Nothing can prepare the human race for what is about to be unleashed in Gasaraki!\r\n(Source: Sunrise)",
      "startedAiringDate":"1998-10-04",
      "finishedAiringDate":"1999-03-28",
      "youtubeVideoId":null,
      "ageRating":"PG13",
      "episodeCount":25,
      "episodeLength":25,
      "showType":"TV",
      "poster_image":"https://static.hummingbird.me/anime/poster_images/000/000/699/large/699.jpg?1408442080",
      "cover_image":"/cover_images/thumb/missing.png",
      "communityRating":3.51530688403304,
      "genres":[
        "Drama",
        "Supernatural",
        "Psychological",
        "Military",
        "Mecha"
      ],
      "producers":[
        "Sunrise",
        "ADV Films",
        "Yomiko Advertising",
        "The Right Stuf International"
      ],
      "bayesian_rating":3.51530688403304,
      "links":{
        "gallery_images":[

        ],
        "episodes":[
          15182,
          15181,
          15180,
          15179,
          15165,
          15163,
          15158,
          15178,
          15177,
          15176,
          15175,
          15173,
          15172,
          15171,
          15170,
          15167,
          15166,
          15164,
          15162,
          15161,
          15160,
          15159,
          15168,
          15169,
          15174
        ]
    },
    "linked":{
  "gallery_images":[

  ],
    "episodes":[
    {
      "id":15182,
      "title":"Gasara",
      "synopsis":"Desperate to summon Gasaraki and claim the infinite power he craves, Kazukiyo decides to use the powers of the third Kai- his little sister Misuzu. Can Yushiro and Miharu arrive in time to stop him, or will they too be drawn into the very terror they have tried so hard to avoid?",
      "airdate":"1999-03-28",
      "number":25,
      "season_number":1
    },
    {
      "id":15181,
      "title":"Punctuation",
      "synopsis":"Having obtained the location of their enemy's network hub, the Americans send 4 TAs to infiltrate and destroy it. It's up to the 3rd Experimentation Company to stop them from succeeding. Nishida's face off with the President of the U.S. comes to a close.",
      "airdate":"1999-03-21",
      "number":24,
      "season_number":1
    },
    {
      "id":15180,
      "title":"Eternal",
      "synopsis":"The Americans, hoping to both provoke the Japanese and gather intel, send TAs to Ichigaya. But they only send one TA and it happens to be Miharu. Yushiro again confronts her, in hopes to dissuade her from harsh actions.",
      "airdate":"1999-03-14",
      "number":23,
      "season_number":1
    },
    {
      "id":15179,
      "title":"Personification",
      "synopsis":"As Nishida's plans of retaliation against America continue to unfold, Yushiro confronts his brother Kazukiyo in regards to his ambitions pertaining to the kai.",
      "airdate":"1999-03-07",
      "number":22,
      "season_number":1
    },
    {
      "id":15165,
      "title":"Inferno",
      "synopsis":"Amid evidence that the Japanese government is involved in some sort of cover-up regarding the TAs, Yushiro's elder brother Kazukiyo Gowa meets with Hiraku Nishida, an elderly, blind warrior who wishes to unleash selective violence against the Japanese people in order to strengthen Japan against growing opportunism and selfishness. At the Gowa laboratory the plot takes a startling twist when Miharu breaks into the Gowa headquarters to retrieve the missing components taken by the Japanese TAs in Belgistan. As she exits the plant, Miharu reveals to Yushiro the startling secret she has uncovered during the raid - the Gowa family refers to Yushiro as \"\"the test subject\"\" and that the real Yushiro Gowa has been deceased for years. As the episode ends, Yushiro's entire existence is mired in uncertainty.",
      "airdate":"1998-11-22",
      "number":8,
      "season_number":1
    },
    {
      "id":15163,
      "title":"The Puppet",
      "synopsis":"The evil organization Symbol institutes a coup in Belgistan to hide evidence of their involvement. The new leadership, lacking knowledge of Symbol's hand in events, immediately pleads for a ceasefire. Yushiro uses the opportunity to sneak deep into Belgistan and meet Miharu face-to-face, and the American government uses the pretext of weapons inspections to gather intelligence on the Gowa Corporation's bipedal weapons.",
      "airdate":"1998-11-08",
      "number":6,
      "season_number":1
    },
    {
      "id":15158,
      "title":"On the Ancient Stage of Stone",
      "synopsis":"Yushiro's movements, as part of a Gowa experiment, bring into existence a singularity sought by the Gowa Consortium. Suddenly, another dancer cries out from elsewhere pleading, \"\"Don't bring back the terror!\"\" Sensing the chilling voice, Yushiro falls out of his trance and the experiment fails.",
      "airdate":"1998-10-04",
      "number":1,
      "season_number":1
    },
    {
      "id":15178,
      "title":"Run",
      "synopsis":"Ataka and Kaburagi run into Yushiro by chance. After learning from him that Miharu's been taken by Symbol, they decide to try and assist him on rescuing her and storm an air base.",
      "airdate":"1999-02-21",
      "number":21,
      "season_number":1
    },
    {
      "id":15177,
      "title":"Upheaval",
      "synopsis":"The 3rd Experimentation Company elects to assist Nishida's ambitions. Their first task is to provide protection detail for the Prime Minister, but things quickly spin out of control.",
      "airdate":"1999-02-14",
      "number":20,
      "season_number":1
    },
    {
      "id":15176,
      "title":"Wails",
      "synopsis":"Nishida and Colonel Hayakawa meet to discuss the possible utilization of the 3rd Experimentation Company in Nishida's plans. Meanwhile, Symbol moves in to recapture Miharu.",
      "airdate":"1999-02-07",
      "number":19,
      "season_number":1
    },
    {
      "id":15175,
      "title":"Rear Window",
      "synopsis":"The Gowa family puts forth a plan to search for Yushiro. Meanwhile, Japan's Prime Minister is persuaded to deploy TAs to assist in controlling an impending riot.",
      "airdate":"1999-01-31",
      "number":18,
      "season_number":1
    },
    {
      "id":15173,
      "title":"Karma",
      "synopsis":"The flashback to the Heian period continues. Tsuna Watanabe leads his clan and his Kugai into battle against the Imperial forces as the two young kais realize they are the tools of those around them. The evil regent Einyo manipulates Tsuna into unleashing the full power of the Kugai. The willpower of Yushiro and Miharu bring an end to the impending terror, which leads to the defeat Einyo and the death of Tsuna. The new leaders of the Watanabe clan agree to bury the secrets of the Kugai and retreat back into the mists of time. In the present, Yushiro and Miharu gain a greater understanding of their place in the continuing legacy of the kai.",
      "airdate":"1999-01-17",
      "number":16,
      "season_number":1
    },
    {
      "id":15172,
      "title":"The Threshold",
      "synopsis":"Yushiro and Miharu follow the long-lost Path of the Kai to the threshold of Kyoto, home to the lost secrets of Yushiro and Miharu's past. Upon touching the threshold, the two young kais are given a glimpse of Japan in the Heian period, around 1,000 A.D.. The story tells of a disagreement in the Watanabe clan, the last protectors of the secret of Gasaraki. This disagreement leads to the accession of warlord Tsuna Watanabe who intends to use his clan's kai, Yushiro and Miharu, to reassert its power in the face of centralizing imperial power.",
      "airdate":"1999-01-10",
      "number":15,
      "season_number":1
    },
    {
      "id":15171,
      "title":"Companions",
      "synopsis":"Freed from the Gowa compound by Daizaburo, Yushiro and Miharu resume their interrupted quest to discover the secret of their mystical kai abilities. Meanwhile, Nishida and Kuzukiyo Gowa plan a military coup that will simultaneously rid Japan of its immigrants and its avarice. In the process Nishida reveals the history of the Kugutsu, individuals who have received special talents from the lost god Gasaraki. These powers allow the Kugutsu, whose members are known as \"\"kai\"\", to operate the Kugai (plural), organic killing machines that function much like giant robots of the near future.",
      "airdate":"1999-01-03",
      "number":14,
      "season_number":1
    },
    {
      "id":15170,
      "title":"Disembark",
      "synopsis":"The Gowa family undergoes a change in leadership, and with the new leader, a new direction with a goal of pitting all of Japan against Symbol. Meanwhile, Yushiro decides to follow the path of the kai in hopes that it will help him remember his past.",
      "airdate":"1998-12-27",
      "number":13,
      "season_number":1
    },
    {
      "id":15167,
      "title":"Kugai",
      "synopsis":"Sensing the proximity of Miharu and the other Symbol Ishtar (known colloquially as Fakes), Yushiro instinctively begins to perform the Gasara Dance. Before long, the stomach of the abandoned robot, known as the Kugai, opens up and takes in Yushiro as a pilot. The robot, adorned like an ancient Japanese samurai, battles and defeats the intruding weapons platforms and captures Miharu. When Yushiro leaves the Kugai, his master, Sorochi, informs him that Yushiro is a kai, a man with the special talent required to control the Kugai, and that the Kugai itself is a gift from Gasaraki. Sorochi says Yushiro must travel to Kyoto with Miharu in order to find out more.",
      "airdate":"1998-12-06",
      "number":10,
      "season_number":1
    },
    {
      "id":15166,
      "title":"Storehouse",
      "synopsis":"In an attempt to understand the information Miharu has imparted to him, Yushiro returns to his family home to confront his parents. At his ancestral home, Yushiro meets with his instructor, Sorachi, who leads Yushiro into an abandoned storehouse. There Yushiro discovers the cryogenically preserved body of Sorochi's son Ku-ya as well as a body that is probably his, along with the slouched form of a formidably-advanced robot, known as the Kugai. Meanwhile, Yushiro's sister, Misuzu, and Ataka, one of the female TA pilots, have followed Yushiro to the site, where they are surprised by the sudden appearance of Miharu and another of the symbol weapons platforms, known as Ishtars.",
      "airdate":"1998-11-29",
      "number":9,
      "season_number":1
    },
    {
      "id":15164,
      "title":"Return",
      "synopsis":"The squad of TA pilots defies orders to rescue Yushiro from Symbol forces in Belgistan. The Gowa Corporation fights to keep their technological secrets and captured enemy TA unit from the snooping Multinational forces and the hostile troops of another fictional post-Soviet Republic, the nation of Armekistan.",
      "airdate":"1998-11-15",
      "number":7,
      "season_number":1
    },
    {
      "id":15162,
      "title":"The Touching",
      "synopsis":"The Tactical Armored units of the Gowa Corporation clash with enemy TAs deep within Belgistanian territory and capture a defeated unit. Meanwhile the public is fascinated by the use of the new bipedal weapon systems, and SNN's very own Ronald Fagan attempts to get live footage for the viewing public.",
      "airdate":"1998-11-01",
      "number":5,
      "season_number":1
    },
    {
      "id":15161,
      "title":"Mirage",
      "synopsis":"The TA units arrived in Belgistan whereupon they are ordered to capture Shrine Hill, the location where the Belgistanians allegedly detonated their weapon of mass destruction. Surprising both the multinational forces and the TA unit, researchers from the Gowa Corporation arrive and confirm that Shrine Hill is virtually identical to Stage of Stone on which Yushiro performed his initial dance. This fact is confirmed when Yushiro enters a trance mode, leaves his TA and begins to perform the Gasara dance on Shrine Hill. Undeniably, a connection exists between Belgistan's release of power and the Gasara dance.",
      "airdate":"1998-10-25",
      "number":4,
      "season_number":1
    },
    {
      "id":15160,
      "title":"Tantric Circle",
      "synopsis":"The Gowa Consortium clears the way for the deployment of the Tactical Armored System to Belgistan, enlisting Yushiro to come along. In the meantime, an American tank battalion faces staggering losses on their incursion into Belgistanian territory, confirming that Belgistan does possess bipedal weapons with formidable attack abilities.",
      "airdate":"1998-10-18",
      "number":3,
      "season_number":1
    },
    {
      "id":15159,
      "title":"Opening Movements",
      "synopsis":"As the Gowa Consortium evaluates the failure of their experiment with Yushiro, the United Nations responds to what seems to be a weapons test in the renegade post-Soviet Republic of Belgistan. After a successful air attack, the allied ground forces suffer a startling defeat, leading the Japanese company to reveal the secret of the Tactical Armor systems. It seems these units may have been responsible for the U.N. defeat at the hands of the Belgistanians.",
      "airdate":"1998-10-11",
      "number":2,
      "season_number":1
    },
    {
      "id":15168,
      "title":"Ties",
      "synopsis":"When Gowa's research on the artificial muscle recovered from Belgistan goes awry, the 3rd Experimentation Company is dispatched to try and resolve the situation. But the rogue pilot they must subdue isn't operating under normal circumstances.",
      "airdate":"1998-12-13",
      "number":11,
      "season_number":1
    },
    {
      "id":15169,
      "title":"Unravel",
      "synopsis":"Yushiro and Miharu are captured by the Gowa Corporation and SSDF. While Miharu is interrogated, Yushiro is charged in trying to succeed in subduing the rogue TA pilot.",
      "airdate":"1998-12-20",
      "number":12,
      "season_number":1
    },
    {
      "id":15174,
      "title":"Chaos",
      "synopsis":"Yushiro and Miharu hide out in the Japanese slums, where they are helped by Wan, a gang member. Wan is actually a former member of Symbol and is recruited by Symbol to capture the two. Meanwhile, Kazukiyo, Hirokawa and Nishida plan their next move with the TAs, knowing that upcoming grain export news from the U.S. is sure to cause panic among the populace.",
      "airdate":"1999-01-24",
      "number":17,
      "season_number":1
    }
  ]
}
    }
  ]
});

//App.AnimesAdapter = DS.RESTAdapter.extend({
//  host: 'https://cors-test.appspot.com'
//headers: {
//  "X-Client-Id": "db9fc918ff38d0235966"
//},
//find: function(modelName){
//  return "ok";
//},
//ajax: function(url, method, hash) {
//  hash = hash || {};
//  hash.crossDomain = true;
//  hash.xhrFields = {withCredentials: true};
//  return this._super(url, method, hash);
//}
//
//

//});

export default Anime;
