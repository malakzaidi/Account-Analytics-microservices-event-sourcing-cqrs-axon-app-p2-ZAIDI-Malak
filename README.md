# Account Analytics - Microservices avec Event Sourcing, CQRS et Axon Framework

## Description du Projet

Ce projet est une application complète de gestion et d'analyse de comptes bancaires, construite avec une architecture microservices moderne. L'application utilise les patterns Event Sourcing et CQRS (Command Query Responsibility Segregation) via Axon Framework pour garantir une traçabilité complète des opérations bancaires et une séparation optimale entre les opérations de lecture et d'écriture.

<img width="1906" height="1046" alt="image" src="https://github.com/user-attachments/assets/4207bd58-a8ec-481e-a039-3acf72a4c0d4" />

Le système permet de créer des comptes bancaires, d'effectuer des opérations de crédit et de débit, de consulter l'historique complet des transactions, et de visualiser des analyses détaillées via un tableau de bord interactif.

### Test des endpoints : API Documentation

**1. Créer un Compte**

<img width="1902" height="1056" alt="Screenshot 2025-12-26 005046" src="https://github.com/user-attachments/assets/a1091547-f78e-4900-af86-b3a75f21fff2" />


**2. Créditer un Compte**

<img width="1915" height="1063" alt="Screenshot 2025-12-26 031109" src="https://github.com/user-attachments/assets/7540860c-be03-4875-90c1-3c68b23b1490" />

<img width="1914" height="1066" alt="Screenshot 2025-12-26 031124" src="https://github.com/user-attachments/assets/40f6496f-bbe7-4888-81b9-87a891815997" />

**3. Débiter un Compte**

<img width="1888" height="983" alt="Screenshot 2025-12-27 142033" src="https://github.com/user-attachments/assets/44d8fe4b-4c1e-41ae-8944-0e471246ad29" />

<img width="1897" height="1052" alt="Screenshot 2025-12-27 142018" src="https://github.com/user-attachments/assets/09900063-63ef-49ec-9218-63740202ece8" />

 - Exception handler au cas ou le solde est insuffisant :

<img width="1906" height="768" alt="Screenshot 2025-12-27 142052" src="https://github.com/user-attachments/assets/2465dfff-1c23-4eb5-a5c3-9151879fbb45" />


**4. Consulter la base de données MySQL** :

<img width="1919" height="564" alt="Screenshot 2025-12-26 031417" src="https://github.com/user-attachments/assets/ddd4bb73-7124-44a5-80d4-fb637ca43bcb" />


**5. Ajout de la table Account dans la base de données MySQL **

<img width="1654" height="850" alt="Screenshot 2025-12-27 171606" src="https://github.com/user-attachments/assets/89a86e8b-dfe0-4bb8-8e82-0eae4e34b7a6" />


**6. Consultation de l'interface Axon : les deux services ont apparu dans le serveur Axon**

![services-in-axon.png](screenshots/services-in-axon.png)


**7. Consultation des événements sur Axon**:

![Axon-events.png](screenshots/Axon-events.png)

- Détails des événements :
  
![event-details.png](screenshots/event-details.png)

**8. Mode Lecture : Tester la consultation des analyses des comptes**:

![account-analytics-endpoint-test.png](screenshots/account-analytics-endpoint-test.png)

![test2.png](screenshots/test2.png)

**9. Mode Lecture : Tester l'endpoint de la visualisation des analyses**:

![test-watch-events.png](screenshots/test-watch-events.png)

**10. Consultation de l'eventStore**:

<img width="1908" height="1045" alt="Screenshot 2025-12-27 010332" src="https://github.com/user-attachments/assets/8e91b97f-2471-43a9-95ae-3275533171e5" />


## Architecture Technique

### Patterns Architecturaux Implémentés

**Event Sourcing**
- Stockage de tous les événements dans un Event Store
- Reconstruction de l'état actuel à partir de l'historique des événements
- Traçabilité complète et immuable de toutes les opérations
- Possibilité de replay et d'audit complet

**CQRS (Command Query Responsibility Segregation)**
- Séparation stricte entre les commandes (write model) et les requêtes (read model)
- Optimisation indépendante des opérations de lecture et d'écriture
- Modèles de données adaptés à chaque type d'opération
- Scalabilité horizontale facilitée

**Architecture Microservices**
- Services indépendants et déployables séparément
- Communication asynchrone via événements
- Couplage faible entre les services
- Résilience et isolation des pannes

**Event-Driven Architecture**
- Communication basée sur les événements
- Réactivité en temps réel
- Découplage des composants
- Extensibilité facilitée

### Stack Technologique

**Backend**
- Java 11+ / Spring Boot 2.x
- Axon Framework 4.x (Event Sourcing & CQRS)
- Spring Data JPA (persistance des données)
- MySQL 8.0 (base de données relationnelle)
- Maven (gestion des dépendances)
- Lombok (réduction du code boilerplate)
- Spring Web (API REST)

**Frontend**
- Angular 12+
- TypeScript
- Bootstrap 4/5 (design responsive)
- Chart.js / Recharts (visualisations graphiques)
- RxJS (programmation réactive)

**Infrastructure & Outils**
- Axon Server (Event Store & Message Router)
- Postman (tests API)
- MySQL Workbench (gestion base de données)

## Structure du Projet

```
account-analytics/
│
├── account-command-side/              # Service de gestion des commandes
│   ├── src/main/java/
│   │   ├── commands/                  # Définition des commandes
│   │   │   ├── CreateAccountCommand
│   │   │   ├── CreditAccountCommand
│   │   │   └── DebitAccountCommand
│   │   ├── aggregates/                # Agrégats Axon
│   │   │   └── AccountAggregate
│   │   ├── events/                    # Événements métier
│   │   │   ├── AccountCreatedEvent
│   │   │   ├── AccountCreditedEvent
│   │   │   └── AccountDebitedEvent
│   │   ├── controllers/               # API REST pour les commandes
│   │   │   └── AccountCommandController
│   │   └── services/                  # Services métier
│   └── resources/
│       └── application.properties
│
├── account-query-side/                # Service de gestion des requêtes
│   ├── src/main/java/
│   │   ├── entities/                  # Entités JPA
│   │   │   ├── Account
│   │   │   └── Operation
│   │   ├── repositories/              # Repositories Spring Data
│   │   │   ├── AccountRepository
│   │   │   └── OperationRepository
│   │   ├── controllers/               # API REST pour les requêtes
│   │   │   └── AccountQueryController
│   │   ├── services/                  # Services de projection
│   │   │   └── AccountEventHandler
│   │   └── dto/                       # Data Transfer Objects
│   └── resources/
│       └── application.properties
│
├── account-common-api/                # API partagée entre services
│   └── src/main/java/
│       ├── commands/                  # DTOs de commandes
│       ├── events/                    # DTOs d'événements
│       ├── queries/                   # DTOs de requêtes
│       └── enums/                     # Énumérations
│           ├── AccountStatus
│           └── OperationType
│
├── analytics-dashboard/               # Application frontend Angular
│   ├── src/
│   │   ├── app/
│   │   │   ├── components/
│   │   │   │   ├── account-list/
│   │   │   │   ├── account-details/
│   │   │   │   ├── operations-list/
│   │   │   │   ├── analytics-chart/
│   │   │   │   └── operation-form/
│   │   │   ├── services/
│   │   │   │   ├── account.service.ts
│   │   │   │   └── operation.service.ts
│   │   │   ├── models/
│   │   │   └── app-routing.module.ts
│   │   └── assets/
│   └── package.json
│
└── axon-server/                       # Configuration Axon Server
    └── config/
        └── axonserver.properties
```

## Fonctionnalités Détaillées

### 1. Gestion des Comptes (Command Side)

**Création de Compte**
- Génération automatique ou manuelle de l'identifiant du compte
- Définition du solde initial
- Sélection de la devise (EUR, USD, MAD, etc.)
- Validation des données avant création
- Émission d'un événement AccountCreatedEvent

**Opérations de Crédit**
- Ajout de fonds au compte
- Validation du montant (positif et non nul)
- Mise à jour du solde
- Enregistrement de l'opération dans l'historique
- Émission d'un événement AccountCreditedEvent

**Opérations de Débit**
- Retrait de fonds du compte
- Vérification du solde disponible
- Protection contre les découverts non autorisés
- Mise à jour du solde
- Émission d'un événement AccountDebitedEvent

### 2. Consultation des Données (Query Side)

**Vue des Comptes**
- Liste de tous les comptes avec pagination
- Filtrage par statut, devise ou solde
- Recherche par identifiant de compte
- Affichage du solde actuel et de la devise

**Historique des Opérations**
- Liste chronologique de toutes les transactions
- Filtrage par type (crédit/débit)
- Filtrage par période (date de début/fin)
- Affichage des détails : date, type, montant, description
- Export des données (CSV, PDF)

**Détails du Compte**
- Informations complètes du compte
- Solde actuel en temps réel
- Date de création
- Statut du compte (actif, suspendu, fermé)
- Statistiques : nombre d'opérations, total des crédits/débits

### 3. Tableau de Bord Analytique (Analytics Dashboard)

### 4. Event Store et Axon Server

**Stockage des Événements**
- Persistance immuable de tous les événements
- Ordre chronologique garanti
- Métadonnées associées (timestamp, agrégat ID, version)
- Possibilité de replay complet

**Interface de Monitoring Axon**
- Visualisation des événements en temps réel
- Exploration de l'Event Store
- Statistiques sur les commandes traitées
- Monitoring des agrégats actifs
- Debugging du flux d'événements

### 5. Base de Données (Query Side)

**Consultation en Direct**
- Visualisation des données en temps réel
- Exécution de requêtes SQL personnalisées
- Vérification de la cohérence des données
- Analyse des performances des requêtes

## Installation et Configuration Détaillées

### Prérequis Système

- **Java Development Kit (JDK)**: Version 11 ou supérieure
- **Maven**: Version 3.6+ pour la gestion des dépendances
- **Node.js**: Version 14+ et npm pour le frontend Angular
- **MySQL**: Version 8.0+ pour la base de données
- **Axon Server**: Version 4.5+ (téléchargeable depuis axoniq.io)
- **Git**: Pour cloner le repository
- **Postman**: Pour tester les endpoints API (optionnel)

