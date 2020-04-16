# MentorArc

6e semestre HE-Arc

# Cahier des charges

## Intro

Application permettant de mettre en relation des étudiants/profs de l'He-Arc pour proposer de l'aide dans des matières enseignées. Il y a 2 types de rôles: Les mentors peuvent proposer leur aide en précisant leurs disponibilités et leur localisation, ainsi que les matières maitrisées. Les poulains peuvent rechercher de l'aide à un endroit/moment donné pour une certaine matière. Les poulains peuvent aussi faire une requête, une demande de soutien si aucun mentor ne propose d'aide à un certain moment donné.

Pour aller plus loin, un système d'emails permettra de notifier les mentors ou les poulains en cas de requête d'aide ou d'aide proposé. Un système d'évaluation est aussi envisageable pour potentiellement proposer des récompenses après un certain nombre d'aides apportés. Aussi, un historique de toutes les interactions permettra aux mentors d'avoir un suivi.

## Objectifs primaires

* Système d'authentification (LDAP)
* Mise en place de 3 rôles:
  * Les mentors
    * élèves mentors
    * professeurs mentors
  * Les poulains
* Création par formulaire:
  * Demande de soutien
  * Proposition de son soutien
* Recherche par matière, localisation, utilisateur, temporalité, ...


## Objectifs secondaires

* En tant que mentor, système de notification par mail en cas de requêtes d'aide dans une matière spécifique
* En tant que poulain, système de notification par mail en cas de disponibilité d'un mentor dans une matière spécifique
* Review (score) lié au mentor
* Historique des aides apportés pour les mentors
* Système de reward quant à son score

## Frameworks & composants

* SpringBoot
* SpringMVC / Thymeleaf
* JPA / Spring Data
* Sécurité avec Spring Security
* Sessions
* mySQL