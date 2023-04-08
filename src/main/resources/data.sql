INSERT INTO genres (id, name)
values (1, 'Action'),
       (2, 'Adventure'),
       (3, 'Comedy'),
       (4,'Crime'),
       (5, 'Drama'),
       (6, 'Fantasy'),
       (7, 'Horror'),
       (8, 'Mystery'),
       (9, 'Romance'),
       (10, 'Science Fiction'),
       (11, 'Thriller');


INSERT INTO series(id, name, description, language, released_year, video_url, image_url, episodes)
values (1, 'La Casa De Papel',
        'To carry out the biggest heist in history, a mysterious man called The Professor recruits a band of eight
robbers who have a single characteristic:  none of them has anything to lose. Five months of seclusion memorizing
every step, every detail,every probability culminate in eleven days locked up in the National Coinage and Stamp Factoryof
Spain, surrounded by police forces and with dozens of hostages in their power, to find out whether theirsuicide wager will
lead to everything or nothing.',
        'Spanish', 2017, 'ZAXA1DV4dtI', 'https://media.flaironline.nl/m/lc31n8yrhj2c.jpg/fb-la-casa-de-papel.jpg', 41),
       (2, 'Ginny & Georgia',
        'Angsty, awkward 15-year-old Ginny Miller often feels more mature than her 30-year-old mother,the irresistible,
dynamic Georgia Miller. After years on the run, Georgia desperately wants to put down roots inpicturesque New England and
give her family something they have never had: a normal life. But it is notall carpool and Kombucha.Georgias past threatens
her and her familys new lifestyle, and Georgia will do anything to protect her family.',
        'English', 2021, 'T-tsnIjKV58','https://image.hant.se/ginny-g-7678870.jpg?imageId=7678870&width=1600&height=838&compression=80',20);


INSERT INTO series_genres(series_id, genre_id)
values (1,1),(1,4),(1,5),(1,8),
       (2,3),(2,5);

INSERT INTO movies(id, name, description, language, released_year, video_url, image_url)
values (1, 'John Wick',
        'Condemned by the tyrannical High Table to be on the run for the rest of his life, deadly assassin
   maestro John Wick (2014) embarks on a Sisyphean mission of suicidal fury to decide his fate after the merciless
   carnage in John Wick: Chapter 3 - Parabellum (2019). At last, John''s violent journey, fuelled by vengeance
   and grief, ultimately leads him to a fateful confrontation with his former employers, the crime masters
   that forced him into exile.',
        'English', 2014, 'C0BMx-qxsP4', 'http://media.comicbook.com/2015/10/johnwick2-153693.png'),
       (2, 'Divergent',
        'Set in a futuristic dystopia where society is divided into five factions that each represent a
   different virtue, teenagers have to decide if they want to stay in their faction or switch to another
   - for the rest of their lives. Tris Prior makes a choice that surprises everyone. ',
        'English', 2014, 'sutgWjz10sM',
        'http://www.scifibloggers.com/wp-content/uploads/divergent-movie-poster-wallpaper-1920x1200.jpg');

INSERT INTO movies_genres(movie_id, genre_id)
values (1,1),(1,4),(1,11),
       (2,10),(2,6),(2,9);



