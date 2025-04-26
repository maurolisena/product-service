db = db.getSiblingDB('product_db');

db.createUser({
    user: 'mlisena',
    pwd: 'A8424628',
    roles: [
        { role: 'readWrite', db: 'product_db' }
    ]
});