-- Trigger que inserta un carrito de compras a un usuario
CREATE OR REPLACE FUNCTION insert_carts_function()
RETURNS TRIGGER AS $$
BEGIN
    -- Inserta un carrito de compras asignado al usuario recién añadido
    INSERT INTO shopping_carts (user_id, creation_date)
    VALUES (NEW.id, NOW());

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER insert_carts
AFTER INSERT ON users
FOR EACH ROW
EXECUTE FUNCTION insert_carts_function();
