# Stock and Sales control system

## Skills and technologies applied

- [ ]  Complete CRUD with Spring web, Spring security, JPA, Tests

### System features

- [ ] Create, Read, Update and delete simple stock items (not composed by N others items)
- [ ] Create, Read, Update and delete composed items: those which are composed by multiple other items in stock
    - The components items in this composition are used to calculate cost value
    - Add or remove composed items propagates it to its components - like changing their current quantity in stock
    - The availability to sell a composed item depends on the availability of all of its components and their respective
      quantities necessary in that composition
    - 