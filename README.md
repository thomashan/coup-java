# coup-java
[![Build Status](https://travis-ci.org/thomashan/coup-java.svg?branch=master)](https://travis-ci.org/thomashan/coup-java)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=thomashan_coup-java&metric=alert_status)](https://sonarcloud.io/dashboard?id=thomashan_coup-java)
[![codecov](https://codecov.io/gh/thomashan/coup-java/branch/master/graph/badge.svg)](https://codecov.io/gh/thomashan/coup-java)
[![Coverage Status](https://coveralls.io/repos/github/thomashan/coup-java/badge.svg?branch=master)](https://coveralls.io/github/thomashan/coup-java?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/974abb2b54ea4b6e817c703ecbad0eac)](https://www.codacy.com/manual/thomashan/coup-java?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=thomashan/coup-java&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/974abb2b54ea4b6e817c703ecbad0eac)](https://www.codacy.com/manual/thomashan/coup-java?utm_source=github.com&utm_medium=referral&utm_content=thomashan/coup-java&utm_campaign=Badge_Coverage)

This is a java implementation of the card game Coup by Rikki Tahta.

## Aim
1.  The aim is to implement card game Coup in java.
2.  Build an AI around the statistics
3.  Add statistics about the current game in play

## Prerequisite
*   jdk8+

## How to play
<https://www.youtube.com/watch?v=lPlBDZnxHQA>

## Quality Control
The following tools are used to improve the project

| Methodology   | Tool       | Web                      |
| ---           | ---        | ---                      |
| CI            | Travisci   | <https://travis-ci.org>  |
| Code analysis | Sonarcloud | <https://sonarcloud.io>  |
| Code analysis | Codecov    | <https://codecov.io>     |
| Code analysis | Coveralls  | <https://coveralls.io>   |
| Codacy        | Codacy     | <https://www.codacy.com> |


## Turn state transitions
Here are some scenarios for turn state transitions: (where P1 denotes player 1, P2 player 2, etc)

*Scenario 1*

`P1 assassinate P2 -> P2 challenge -> P2 lost -> P2 reveal card -> P2 no block -> P2 reveal card`

`WaitingMainActionState -> WaitChallengeMainActionState -> WaitingRevealCardState -> WaitingBlockActionState -> WaitingRevealCardState -> CompleteState`

*Scenario 2*

`P1 steal P2 -> P3 challenge -> P3 lost -> P3 reveal card -> P2 block -> P1 challenge -> P1 reveal card`

`WaitingMainActionState -> WaitChallengeMainActionState -> WaitingRevealCardState -> WaitingBlockActionState -> WaitingChallengeBlockActionState -> WaitingRevealCardState -> CompleteState`

*Scenario 3*

`P1 exchange -> P2 challenge -> P2 lost -> P2 reveal card -> P1 pick cards from deck -> P1 put back cards to deck`

`WaitingMainActionState -> WaitChallengeMainActionState -> WaitingRevealCardState -> WaitingPutBackCardsActionState -> CompleteState`

