INSERT INTO public.admin (adminid, password, username) VALUES (1, 'password', 'admin');
INSERT INTO public.admin (adminid, password, username) VALUES (2, 'password', 'superuser');
INSERT INTO public.admin (adminid, password, username) VALUES (3, 'password', 'root');

INSERT INTO public.equipment (equipmentid, description, equipment_name, full_day_price, height, length, per_hr_price, per_trip_price, rate_after10price, rate_after5price, sundayphprice, transportid, url, width) VALUES (2, 'Drive Away', '10 Ton Lorry Crane', 320, 60, 60, 40, 120, 80, 60, 80, 1, 'https://5.imimg.com/data5/IV/WS/MY-24389568/heavy-duty-truck-mounted-500x500.jpg', 60);
INSERT INTO public.equipment (equipmentid, description, equipment_name, full_day_price, height, length, per_hr_price, per_trip_price, rate_after10price, rate_after5price, sundayphprice, transportid, url, width) VALUES (3, 'Drive Away', '15 Ton Lorry Crane', 350, 90, 90, 45, 130, 90, 68, 90, 1, 'https://5.imimg.com/data5/IV/WS/MY-24389568/heavy-duty-truck-mounted-500x500.jpg', 90);
INSERT INTO public.equipment (equipmentid, description, equipment_name, full_day_price, height, length, per_hr_price, per_trip_price, rate_after10price, rate_after5price, sundayphprice, transportid, url, width) VALUES (4, 'Drive Away', '20 Ton Lorry Crane', 430, 120, 120, 55, 160, 110, 83, 110, 1, 'https://5.imimg.com/data5/IV/WS/MY-24389568/heavy-duty-truck-mounted-500x500.jpg', 120);
INSERT INTO public.equipment (equipmentid, description, equipment_name, full_day_price, height, length, per_hr_price, per_trip_price, rate_after10price, rate_after5price, sundayphprice, transportid, url, width) VALUES (5, 'Drive Away', '25 Ton Lorry Crane', 480, 150, 150, 60, 180, 120, 90, 120, 1, 'https://5.imimg.com/data5/IV/WS/MY-24389568/heavy-duty-truck-mounted-500x500.jpg', 150);
INSERT INTO public.equipment (equipmentid, description, equipment_name, full_day_price, height, length, per_hr_price, per_trip_price, rate_after10price, rate_after5price, sundayphprice, transportid, url, width) VALUES (6, 'Drive Away', '30 Ton Lorry Crane', 720, 180, 180, 90, 270, 180, 135, 180, 1, 'https://5.imimg.com/data5/IV/WS/MY-24389568/heavy-duty-truck-mounted-500x500.jpg', 180);
INSERT INTO public.equipment (equipmentid, description, equipment_name, full_day_price, height, length, per_hr_price, per_trip_price, rate_after10price, rate_after5price, sundayphprice, transportid, url, width) VALUES (1, 'Drive Away', '3 Ton Lorry Crane', 220, 30, 30, 27, 120, 54, 40, 54, 1, 'https://5.imimg.com/data5/IV/WS/MY-24389568/heavy-duty-truck-mounted-500x500.jpg', 30);
INSERT INTO public.equipment (equipmentid, description, equipment_name, full_day_price, height, length, per_hr_price, per_trip_price, rate_after10price, rate_after5price, sundayphprice, transportid, url, width) VALUES (7, 'Needs Transportation', '10ft Lorry', 100, 30, 30, 20, 60, 40, 35, 40, 2, 'https://antbuildz.com/images/thumb-800-09eb744f-bf3c-4185-87bb-82ac1da13f9e.jpg', 30);
INSERT INTO public.equipment (equipmentid, description, equipment_name, full_day_price, height, length, per_hr_price, per_trip_price, rate_after10price, rate_after5price, sundayphprice, transportid, url, width) VALUES (8, 'Needs Transportation', '14ft Lorry', 130, 50, 50, 35, 90, 60, 65, 60, 2, 'https://antbuildz.com/images/thumb-800-09eb744f-bf3c-4185-87bb-82ac1da13f9e.jpg', 50);
INSERT INTO public.equipment (equipmentid, description, equipment_name, full_day_price, height, length, per_hr_price, per_trip_price, rate_after10price, rate_after5price, sundayphprice, transportid, url, width) VALUES (9, 'Needs Transportation', '17ft Lorry', 150, 80, 70, 50, 110, 70, 85, 70, 3, 'https://antbuildz.com/images/thumb-800-09eb744f-bf3c-4185-87bb-82ac1da13f9e.jpg', 80);
INSERT INTO public.equipment (equipmentid, description, equipment_name, full_day_price, height, length, per_hr_price, per_trip_price, rate_after10price, rate_after5price, sundayphprice, transportid, url, width) VALUES (10, 'Needs Transportation', '24ft Lorry', 190, 110, 100, 65, 140, 100, 105, 100, 3, 'https://antbuildz.com/images/thumb-800-09eb744f-bf3c-4185-87bb-82ac1da13f9e.jpg', 110);
INSERT INTO public.equipment (equipmentid, description, equipment_name, full_day_price, height, length, per_hr_price, per_trip_price, rate_after10price, rate_after5price, sundayphprice, transportid, url, width) VALUES (11, 'Drive Away', 'Trailer Truck', 350, 90, 90, 50, 110, 60, 50, 60, 1, 'https://www.wileymetal.com/wp-content/uploads/2018/09/semi-trailer-300x213.png', 90);
INSERT INTO public.equipment (equipmentid, description, equipment_name, full_day_price, height, length, per_hr_price, per_trip_price, rate_after10price, rate_after5price, sundayphprice, transportid, url, width) VALUES (12, 'Drive Away', 'Lowbed Trailer', 300, 90, 90, 65, 140, 70, 60, 70, 1, 'https://www.sinotrukhowo.cn/wp-content/uploads/2018/10/Axle-Lowbed-Trailer.jpg', 90);


INSERT INTO public.listing (listingid, equipmentid, partnerid) VALUES (1, 1, 1);
INSERT INTO public.listing (listingid, equipmentid, partnerid) VALUES (2, 1, 2);
INSERT INTO public.listing (listingid, equipmentid, partnerid) VALUES (3, 2, 1);
INSERT INTO public.listing (listingid, equipmentid, partnerid) VALUES (4, 2, 2);
INSERT INTO public.listing (listingid, equipmentid, partnerid) VALUES (5, 3, 1);
INSERT INTO public.listing (listingid, equipmentid, partnerid) VALUES (6, 3, 2);
INSERT INTO public.listing (listingid, equipmentid, partnerid) VALUES (7, 4, 1);

INSERT INTO public.partner (partnerid, userid) VALUES (1, 2);
INSERT INTO public.partner (partnerid, userid) VALUES (2, 5);

INSERT INTO public.publicholiday (dateid, date, description) VALUES (1, '2021-05-01', 'Labour Day 2021');
INSERT INTO public.publicholiday (dateid, date, description) VALUES (2, '2021-05-13', 'Hari Raya Puasa 2021');
INSERT INTO public.publicholiday (dateid, date, description) VALUES (3, '2021-05-26', 'Vesak Day 2021');
INSERT INTO public.publicholiday (dateid, date, description) VALUES (4, '2021-07-20', 'Hari Raya Haji 2021');
INSERT INTO public.publicholiday (dateid, date, description) VALUES (5, '2021-08-09', 'National Day	2021');
INSERT INTO public.publicholiday (dateid, date, description) VALUES (6, '2021-11-04', 'Deepavali 2021');
INSERT INTO public.publicholiday (dateid, date, description) VALUES (7, '2021-12-25', 'Christmas Day 2021');

INSERT INTO public.transport (transportid, capacity, transport_name) VALUES (1, 0, 'No Transport');
INSERT INTO public.transport (transportid, capacity, transport_name) VALUES (2, 10, '10 Ton Transport Vehicle');
INSERT INTO public.transport (transportid, capacity, transport_name) VALUES (3, 20, '20 Ton Transport Vehicle');
INSERT INTO public.transport (transportid, capacity, transport_name) VALUES (4, 30, '30 Ton Transport Vehicle');
INSERT INTO public.transport (transportid, capacity, transport_name) VALUES (5, 40, '40 Ton Transport Vehicle');
INSERT INTO public.transport (transportid, capacity, transport_name) VALUES (6, 50, '50 Ton Transport Vehicle');
INSERT INTO public.transport (transportid, capacity, transport_name) VALUES (7, 60, '60 Ton Transport Vehicle');
INSERT INTO public.transport (transportid, capacity, transport_name) VALUES (8, 70, '70 Ton Transport Vehicle');

INSERT INTO public.users (userid, activated, email, name, partner_request, password, token, username) VALUES (1, false, 'gordon.lim.2018@sis.smu.edu.sg', 'gordon', false, 'P@ssw0rd1', 'e451608a-ceac-4cdf-8de1-eec0769aba5f', 'gordon.lim.2018');
INSERT INTO public.users (userid, activated, email, name, partner_request, password, token, username) VALUES (2, false, 'ying.ci.2018@sis.smu.edu.sg', 'ying ci', false, 'P@ssw0rd1', '7b9f3242-0db2-4394-9018-49e94616340e', 'ying.ci.2018');
INSERT INTO public.users (userid, activated, email, name, partner_request, password, token, username) VALUES (3, true, 'glenn.yeo.2018@sis.smu.edu.sg', 'glenn yeo', true, 'P@ssw0rd1', '547b7621-f53c-4124-892c-ec6c6a27e600', 'glenn.yeo.2018');
INSERT INTO public.users (userid, activated, email, name, partner_request, password, token, username) VALUES (4, false, 'kwan.yang.2018@sis.smu.edu.sg', 'kwan yang', false, 'P@ssw0rd1', 'ded26f1a-3d0e-4271-8502-7acbd0043dcb', 'kwan.yang.2018');
INSERT INTO public.users (userid, activated, email, name, partner_request, password, token, username) VALUES (5, true, 'yeow.leong.2018@sis.smu.edu.sg', 'lee yeow leong', true, 'P@ssw0rd1', 'd4f03bd4-219e-4946-8f31-325a5661140b', 'yeow.leong.2018');


