INSERT INTO utente (username, password, email, salt, Livello) VALUES
('admin', 'admin', 'admin.admin@admin.com', '1234567891234567', '0'),
('Edoardo23', 'Gervaso23', 'egosho.ageg@gmail.com', 'sdgesihgoghgigwh', '1'),
('Giechician', 'hician', 'egegag.wgaò@gmail.com', 'awfòqoifhsefoggw', '1');

INSERT INTO post (data_ora, testo, titolo, utente_id) VALUES
('2020-01-01 10:32:21', 'Serie A', 'La Juve vola in testa alla classifica rubando!', '1'),
('2123-12-23 14:31:41', 'Nuoto', 'Federica Pellegrini è la nuova campionessa del mondo!', '1'),
('2566-01-31 17:42:34', 'Formula 1', 'Sebastian Vettel vince il gran premio di Monza davanti ai tifosi del Cavallino', '1');

INSERT INTO commento (data_ora, testo, titolo, utente_id, post_id) VALUES
('2020-01-01 10:41:17', 'Vergogna!!', 'Dovrebbero vergognarsi, rubare così spudoratamente!', '2', '1'),
('2123-12-23 14:31:41', 'Grande Fede!', 'Meno male che abbiamo ancora qualche orgoglio italiano...', '3', '2'),
('2566-01-31 17:42:34', 'Forza Rossa!', 'Il mondiale lo vince la rossa!!', '2', '3');
