INSERT INTO menu (menu_name, description, price, category, chef_choice, gluten_free, lactose_free, contains_nuts, vegan,
                  vegetarian)
VALUES ('Prime Ribeye Steak', 'Dry-aged ribeye with rich marbling, grilled over high heat and served with herb butter.',
        54.00, 'Prime Cuts', true, true, false, false, false, false);

INSERT INTO menu (menu_name, description, price, category, chef_choice, gluten_free, lactose_free, contains_nuts, vegan,
                  vegetarian)
VALUES ('Tomahawk Steak for Two', 'Large bone-in rib steak, carved tableside and served with roasted garlic jus.',
        118.00, 'Prime Cuts', true, true, true, false, false, false);

INSERT INTO menu (menu_name, description, price, category, chef_choice, gluten_free, lactose_free, contains_nuts, vegan,
                  vegetarian)
VALUES ('Filet Mignon', 'Tender center-cut beef filet with red wine reduction and seasonal vegetables.', 62.00,
        'Prime Cuts', true, true, true, false, false, false);

INSERT INTO menu (menu_name, description, price, category, chef_choice, gluten_free, lactose_free, contains_nuts, vegan,
                  vegetarian)
VALUES ('Beef Short Ribs', 'Slow-braised beef short ribs with smoked demi-glace and creamy mashed potatoes.', 39.50,
        'Beef Classics', false, false, false, false, false, false);

INSERT INTO menu (menu_name, description, price, category, chef_choice, gluten_free, lactose_free, contains_nuts, vegan,
                  vegetarian)
VALUES ('Steak Tartare', 'Hand-cut beef tenderloin with capers, shallots, mustard, egg yolk and toasted bread.', 29.00,
        'Starters', false, false, true, false, false, false);

INSERT INTO menu (menu_name, description, price, category, chef_choice, gluten_free, lactose_free, contains_nuts, vegan,
                  vegetarian)
VALUES ('Wagyu Beef Burger', 'Juicy wagyu beef patty with aged cheddar, caramelized onions and steakhouse sauce.',
        32.50, 'Beef Classics', false, false, false, false, false, false);

INSERT INTO menu (menu_name, description, price, category, chef_choice, gluten_free, lactose_free, contains_nuts, vegan,
                  vegetarian)
VALUES ('Large salad', 'Large mixed salad with italian dressing', '15.00', 'Vegetarian maincourse', false, false, true,
        false, true, true);

INSERT INTO reservation (customer_name, phone_number, number_of_persons, reserved_from, reserved_to, table_id)
VALUES ('Lukas Steiner', '+41791234567', 4, '2026-06-15 18:30:00', '2026-06-15 20:30:00', 'T-03');

INSERT INTO reservation (customer_name, phone_number, number_of_persons, reserved_from, reserved_to, table_id)
VALUES ('Sofia Keller', '+41769876543', 2, '2026-06-16 20:00:00', '2026-06-16 21:30:00', 'T-01');

INSERT INTO reservation (customer_name, phone_number, number_of_persons, reserved_from, reserved_to, table_id)
VALUES ('Marc Baumann', '+41765550112', 6, '2026-06-17 19:00:00', '2026-06-17 21:30:00', 'T-03');

INSERT INTO reservation (customer_name, phone_number, number_of_persons, reserved_from, reserved_to, table_id)
VALUES ('Anna Weber', '+41795550844', 3, '2026-06-18 18:00:00', '2026-06-18 20:00:00', 'T-01');

INSERT INTO reservation (customer_name, phone_number, number_of_persons, reserved_from, reserved_to, table_id)
VALUES ('David Rossi', '+41764443322', 5, '2026-06-19 20:15:00', '2026-06-19 22:15:00', 'T-03');
