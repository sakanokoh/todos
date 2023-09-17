Feature: Gestion des tags

  Background:
    Given table tags is empty

  Scenario: Créer un nouveau tag
    Given name = "Nouveau Tag"
    And description = "Description du Nouveau Tag"
    When call add tag
    Then The http status is 201
    And the created tag has properties name="Nouveau Tag", description="Description du Nouveau Tag", completed="false"

  Scenario: Obtenir la liste de tous les tags
    Given table tags contains data:
      | name              | description         |
      | Tag1              | Description du Tag1 |
      | Tag2              | Description du Tag2 |
    When call find all
    Then The http status is 200
    And the returned list has 2 elements
    And that list contains values: name="Tag1" and description="Description du Tag1"
    And that list contains values: name="Tag2" and description="Description du Tag2"

  Scenario: Obtenir un tag par son ID
    Given table tags contains data:
      | id                | name             | description         |
      | 12345             | Tag3              | Description du Tag3 |
    And id = "12345"
    When call find by id
    Then The http status is 200
    And the returned tag has properties name="Tag3", description="Description du Tag3"

  Scenario: Mettre à jour un tag existant
    Given table tags contains data:
      | id                | name             | description         |
      | 67890             | Tag4              | Description du Tag4 |
    And id = "67890"
    And name = "Nouveau Titre"
    And description = "Nouvelle Description"
    When call update tag
    Then The http status is 202
    And the updated tag has properties name="Nouveau Titre", description="Nouvelle Description"

  Scenario: Supprimer un tag existant
    Given table tags contains data:
      | id                | name             | description         |
      | 54321             | Tag5              | Description du Tag5 |
    And id = "54321"
    When call delete tag
    Then The http status is 204

  Scenario: Supprimer un tag inexistant
    Given table tags contains data:
      | id                | name             | description         |
      | 12345             | Tag6              | Description du Tag6 |
    And id = "99999"
    When call delete tag
    Then The http status is 404
