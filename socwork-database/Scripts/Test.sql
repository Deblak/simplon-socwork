SELECT * FROM t_accounts;
SELECT * FROM t_roles;

SELECT 
    a.id AS account_id,
    a.username AS account_username,
    a.password AS account_password,
    r.role_id AS role_id,
    r.name AS role_name
FROM 
    t_accounts a
JOIN 
    t_account_roles ar ON a.id = ar.account_id
JOIN 
    t_roles r ON ar.role_id = r.role_id;