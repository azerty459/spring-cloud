insert into category(id, name, parent_id) VALUES
    (1, 'Bricolage', null),
    (2, 'Bijoux', null),
    (3, 'Bague', 2);

insert into product(id, description, label, category_id) VALUES
    (1, 'Bague en argent Pandora pour femme', 'Bague en argent', 3),
    (2, 'Marteau de menuisier Stanley jaune et noir', 'Marteau', 1);
