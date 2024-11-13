#INSERTS PARENT PRODUCTS
INSERT INTO rd_stock_db.parent_products
(id, name, description, created_at, company_id)
VALUES(1, 'sushi harakiri', 'sushi para matarce', '2024-11-06 21:49:30.000', '923534');
INSERT INTO rd_stock_db.parent_products
(id, name, description, created_at, company_id)
VALUES(2, 'sushi samuari', 'sushi que te mata', '2024-11-06 21:49:30.000', '923534');
INSERT INTO rd_stock_db.parent_products
(id, name, description, created_at, company_id)
VALUES(3, 'sushi ninja', 'sushi que te mata pero en silencio', '2024-11-06 21:49:30.000', '923534');
INSERT INTO rd_stock_db.parent_products
(id, name, description, created_at, company_id)
VALUES(4, 'sushi navio', 'barco con muchos sushis', '2024-11-06 21:49:30.000', '923534');

INSERT INTO rd_stock_db.product_versions
(id, version_id, name, description, filename, created_at, price, is_available, product_id, is_package)
VALUES(1, '8894b7d0-fc14-4e21-9797-8bc59dd1c86e', 'sushi harakiri', 'sushi para matarce', 'sushi-harakiri.jpg', '2024-11-06 21:48:23.000', 30000.00, 1, 1, 0);
INSERT INTO rd_stock_db.product_versions
(id, version_id, name, description, filename, created_at, price, is_available, product_id, is_package)
VALUES(2, '037fa06d-a2d4-4e76-bfa1-85c9ba76f65d', 'sushi samurai', 'sushi que te mata', 'sushi-samurai.jpg', '2024-11-06 21:48:23.000', 40000.00, 1, 2, 0);
INSERT INTO rd_stock_db.product_versions
(id, version_id, name, description, filename, created_at, price, is_available, product_id, is_package)
VALUES(5, 'ce20e391-b219-44b6-9861-f6c1333102aa', 'sushi ninja', 'sushi que te mata pero en silencio', 'sushi-ninja.jpg', '2024-11-06 21:48:23.000', 40000.00, 1, 3, 0);
INSERT INTO rd_stock_db.product_versions
(id, version_id, name, description, filename, created_at, price, is_available, product_id, is_package)
VALUES(7, '48723518-ccbd-43cb-a035-1c08f7a55256', 'Sushi Navio', 'Barco de muchos sushis', 'sushi-navio.jpg', '2024-11-06 21:48:23.000', 100000.00, 1, 4, 1);

#INSERTS OPTION CATEGORIES
INSERT INTO rd_stock_db.option_categories
(id, name, description, label, company_id)
VALUES(1, 'salsas para tu sushi', 'Seleccion de salsas para tu sushi', 'Selecciona tus salsas', '923534');
INSERT INTO rd_stock_db.option_categories
(id, name, description, label, company_id)
VALUES(2, 'seleccion de acompañantes', 'Selecciona tus mejores acompañantes', 'Selecciona tus mejores acompañantes', '923534');
INSERT INTO rd_stock_db.option_categories
(id, name, description, label, company_id)
VALUES(3, 'Seleccion de bebidas', 'Seleccion de bebida', 'Selecciona tu bebida', '923534');
INSERT INTO rd_stock_db.option_categories
(id, name, description, label, company_id)
VALUES(4, 'Seleccion de sushis', 'Seleccion de sushis', 'Selecciona tus sushi preferidos', '923534');
INSERT INTO rd_stock_db.option_categories
(id, name, description, label, company_id)
VALUES(5, 'Salsas basicas', 'Seleccion de salsas basicas', 'Selecciona tus salsas', '923534');

#INSERTS ADDITIONS
INSERT INTO rd_stock_db.additions
(id, name, price, file_name, company_id)
VALUES(1, 'palillos', 0.00, 'palillos.jpg', '923534');
INSERT INTO rd_stock_db.additions
(id, name, price, file_name, company_id)
VALUES(2, 'gaseosa', 3000.00, 'gaseosa.jpg', '923534');
INSERT INTO rd_stock_db.additions
(id, name, price, file_name, company_id)
VALUES(3, 'salsa de anguila', 0.00, 'el-sauce.jpg', '923534');
INSERT INTO rd_stock_db.additions
(id, name, price, file_name, company_id)
VALUES(4, 'salsa de soya', 0.00, 'soy-sauce.jpg', '923534');
INSERT INTO rd_stock_db.additions
(id, name, price, file_name, company_id)
VALUES(5, 'salsa samurai', 4000.00, 'samurai-sauce.jpg', '923534');
INSERT INTO rd_stock_db.additions
(id, name, price, file_name, company_id)
VALUES(6, 'jengibre', 0.00, 'jenjibre.jpg', '923534');
INSERT INTO rd_stock_db.additions
(id, name, price, file_name, company_id)
VALUES(7, 'te helado', 5000.00, 'cold-tea.jpg', '923534');

#INSERTS OPTION CATEGORIES FOR ADDITIONS
INSERT INTO rd_stock_db.option_categories_additions
(option_category_id, addition_id)
VALUES(2, 1);
INSERT INTO rd_stock_db.option_categories_additions
(option_category_id, addition_id)
VALUES(3, 2);
INSERT INTO rd_stock_db.option_categories_additions
(option_category_id, addition_id)
VALUES(1, 3);
INSERT INTO rd_stock_db.option_categories_additions
(option_category_id, addition_id)
VALUES(5, 3);
INSERT INTO rd_stock_db.option_categories_additions
(option_category_id, addition_id)
VALUES(1, 4);
INSERT INTO rd_stock_db.option_categories_additions
(option_category_id, addition_id)
VALUES(5, 4);
INSERT INTO rd_stock_db.option_categories_additions
(option_category_id, addition_id)
VALUES(1, 5);
INSERT INTO rd_stock_db.option_categories_additions
(option_category_id, addition_id)
VALUES(2, 6);
INSERT INTO rd_stock_db.option_categories_additions
(option_category_id, addition_id)
VALUES(3, 7);

#INSERTS OPTION_CATEGORIES FOR PRODUCT VERSIONS
INSERT INTO rd_stock_db.option_categories_product_versions
(product_version_id, option_category_id)
VALUES(1, 1);
INSERT INTO rd_stock_db.option_categories_product_versions
(product_version_id, option_category_id)
VALUES(1, 2);
INSERT INTO rd_stock_db.option_categories_product_versions
(product_version_id, option_category_id)
VALUES(1, 3);
INSERT INTO rd_stock_db.option_categories_product_versions
(product_version_id, option_category_id)
VALUES(2, 2);
INSERT INTO rd_stock_db.option_categories_product_versions
(product_version_id, option_category_id)
VALUES(2, 3);
INSERT INTO rd_stock_db.option_categories_product_versions
(product_version_id, option_category_id)
VALUES(2, 5);
INSERT INTO rd_stock_db.option_categories_product_versions
(product_version_id, option_category_id)
VALUES(5, 1);
INSERT INTO rd_stock_db.option_categories_product_versions
(product_version_id, option_category_id)
VALUES(5, 2);
INSERT INTO rd_stock_db.option_categories_product_versions
(product_version_id, option_category_id)
VALUES(5, 3);
INSERT INTO rd_stock_db.option_categories_product_versions
(product_version_id, option_category_id)
VALUES(7, 1);
INSERT INTO rd_stock_db.option_categories_product_versions
(product_version_id, option_category_id)
VALUES(7, 2);
INSERT INTO rd_stock_db.option_categories_product_versions
(product_version_id, option_category_id)
VALUES(7, 3);
INSERT INTO rd_stock_db.option_categories_product_versions
(product_version_id, option_category_id)
VALUES(7, 4);
INSERT INTO rd_stock_db.option_categories_product_versions
(product_version_id, option_category_id)
VALUES(1, 4);
INSERT INTO rd_stock_db.option_categories_product_versions
(product_version_id, option_category_id)
VALUES(2, 4);
INSERT INTO rd_stock_db.option_categories_product_versions
(product_version_id, option_category_id)
VALUES(5, 4);

select *
from parent_products pp;

select *
from product_versions pv;

select *
from additions a;

select *
from option_categories;

select oc.id, oc.label, oc.name, a.id,  a.name, a.price
from option_categories_additions oca
inner join option_categories oc on oc.id = oca.option_category_id
inner join additions a on a.id = oca.addition_id;

#If is_package = false
select pv.id, pv.name, oc.id, oc.name, a.id, a.name, a.price
from option_categories_product_versions ocpv
inner join product_versions pv on pv.id = ocpv.product_version_id
inner join option_categories oc on oc.id = ocpv.option_category_id
inner join option_categories_additions oca on oc.id = oca.option_category_id
inner join additions a on a.id = oca.addition_id
where pv.id = 5;

#If is_package = true
select pv.id, pv.name, oc.id, oc.name, a.id, a.name, a.price
from option_categories_product_versions ocpv
inner join product_versions pv on pv.id = ocpv.product_version_id
inner join option_categories oc on oc.id = ocpv.option_category_id
inner join option_categories_additions oca on oc.id = oca.option_category_id
inner join additions a on a.id = oca.addition_id
where pv.id = 7;

select oc.id, oc.name, pv.id, pv.name
from option_categories_product_versions ocpv
inner join product_versions pv on pv.id = ocpv.product_version_id
inner join option_categories oc on oc.id = ocpv.option_category_id
where ocpv.option_category_id in (
	(
	    select oc.id
		from option_categories_product_versions ocpv
		inner join product_versions pv on pv.id = ocpv.product_version_id
		inner join option_categories oc on oc.id = ocpv.option_category_id
		where pv.id = 7
	)
	except 
	(
	    select oca.option_category_id 
		from option_categories_additions oca
	)
) and pv.is_package = 0;


	


