INSERT INTO users
("name", email) VALUES
('Sônia Daiane Jennifer Ferreira', 'sonia-ferreira91@corp.inf.br'),
('Geraldo Benício Benício Pereira', 'geraldobeniciopereira@huios.com.br'),
('Aparecida Cecília Luiza Castro', 'aparecida.cecilia.castro@bravo.com.br'),
('Victor Antonio Assunção', 'victor-assuncao80@lta-am.com.br'),
('Sérgio Henrique Jorge Bernardes', 'sergiohenriquebernardes@grupompe.com.br'),
('Iago Guilherme Ian Sales', 'iagoguilhermesales@poligerma.com.br');


INSERT INTO categories
("name") VALUES
('ECOS'),
('CEM'),
('Ciência da Computação'),
('Engenharia'),
('Arquitetura'),
('Direito');


INSERT INTO subscriptions
("token", user_id) VALUES
('dkZZR9UOs6GFawtvsTZB_i:APA91bHZAJvV4HZNZp2cxN94gQUOlo02SCnpPlSdvFgYpiI1BqeUi1x2SrWj0KFdtqjOs8wtGCl0NJsG7auiVRaI0evTG07QQh0_ayS7Tz246gumMGtlfO1iRKn7z8V81IbepNIFWbf0', 1),
('c7SlF5yZq3ipaR-G2OQrNs:APA91bHfc8_HvSyB7yiknkv_cD-_m_FQ3Ev8QXbG3F-Vm8fP0EkWVIx2IXiqwKdFRG0LdHtzOrw9RhA3MYs9LXYT7sxKDhZ8co5ewDhygJ3KEVqesmuorkC4wYWczVPYBtf00xrsQk7y', 2),
('f9jvakupngnxJkQr_tIWGI:APA91bGE_Dsk5XGel3zWo1coMCZWNbeg_59VqK5DS-wwmr0z849t2-jHgZkLhhgqUMoN40UZ05vyL8i6MBZnZ7b1Bs_6jLnUC_OBST0GMdn71-jqocFaipgDnwIOogq0EpB9TKn8BVe5', 3),
('c6PQEEEtYRfWALwaxKoDNT:APA91bGnfoJ6XkEJ14jJAN_c_EXVDY1ndcM3Is-4xa5jWYxdw83mTptiW9OVJlknjilCMJcfA3BWYYWAAKc-jbA0JZ-N66YqR8Fxnj7AfGT-iVPPnruNLc8RH1_IerBhNaxC0YC-B3LA', 4),
('cNdGC2jnrpwKUmTsxAe0vW:APA91bGn1hjs6peoVdKxQV9SlNCRopbZTmwaxFxRXc9j3PBl59DSrJc3MW97JjmeEyZ0usfFgXTWW8KRPfPgYoPuFCbAvr3XoI4srB3-FYKtQkggReVmADHVEpTb_W-8vPNncQWhpNTr', 5),
('e_hwo8ihUfcq3rq0znyGop:APA91bGERz9tvT6rqFHJg0B4Pc-vmd6yWU_TMFmVDJ0quMvJ9tfrlLXwumKIfpDydKfN7cVIfzWFXyUJr-Y4NsIKA_ibFO49h9yqdGIupQ2Y-_RsFyl1VC1qITqGUObAw6PwArRdRlcG', 6);


INSERT INTO users_category
(user_id, category_id) VALUES
(1, 1),
(1, 3),
(2, 4),
(2, 2),
(3, 6),
(4, 5),
(4, 1),
(5, 5),
(5, 1),
(6, 3),
(6, 2);


INSERT INTO notifications
(title, category_id, message, "date", image_url) VALUES
('Reunião do ECOS', 1, 'Reunião sobre projeto Ecos, falando sobre todos os assuntos e açoes das medidas por parte dos alunos', '2022-03-25', ''),
('Final do CEM 2022 ', 2, 'ATENÇÃO passageiros da nave CEM! A grande final de Matemática(CEM) 2022 foi antecipada para o dia 30', '2022-03-26', ''),
('Lembrete de atividade', 3, 'Não se esqueçam da atividade avaliativa', '2022-03-30', '');
