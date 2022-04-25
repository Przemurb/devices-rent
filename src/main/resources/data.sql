INSERT INTO
    category(name, description)
VALUES
    ('Śrubokręty', 'Śrubokręty ręczne płaskie i krzyżakowe'),
    ('Młotki', 'Młotki ręczne do 2 kg'),
    ('Obcęgi', 'Obcążki małe i duże'),
    ('Wiertarki', 'Wiertarki ręczne i elektryczne');

INSERT INTO
    customer(first_name, last_name, pesel, document_number)
VALUES
    ('Jan', 'Janowski', '123456', 'ABC123456'),
    ('Zosia', 'Zosialska', '09876', 'QWE3456'),
    ('Franek', 'Frankowski', '747292', 'ERT53545'),
    ('Julia', 'Julkowksa', '235637', 'FGT456456');

INSERT INTO
    device(name, description, quantity, price, category_id)
VALUES
    ('Śrubokręt płaski', 'Śrubokręt płaski 2 mm', 3, 8.00, 1),
    ('Śrubokręt krzyżakowy', 'Śrubokręt krzyżakowy  mm', 3, 12.00, 1),
    ('Młotek mały', 'Młotek 0,5 kg', 2, 25.20, 2),
    ('Młotek duży', 'Młotek 2 kg', 1, 48.60, 2),
    ('Obcęgi płaskie', 'Obcęgi płaskie małe', 5, 18.00, 3),
    ('Wiertarka ręczna', 'Mała wiertarka ręczna', 2, 125.00, 4),
    ('Wiertarka udarowa 1000W', 'Mała wiertarka elektryczna z udarem, 1000W', 4, 396.00, 4);

