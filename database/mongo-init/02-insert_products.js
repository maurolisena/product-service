db = db.getSiblingDB('product_db');

db.products.insertMany([
    {
        name: "Filtro de aceite",
        code: "FO-001",
        description: "Filtro para sistema de lubricación de motor.",
        price: 10.99,
        active: true,
        createdAt: new Date("2024-06-10T10:00:00Z"),
        updatedAt: new Date("2024-07-01T14:30:00Z")
    },
    {
        name: "Pastillas de freno",
        code: "PF-002",
        description: "Pastillas para frenos delanteros.",
        price: 25.50,
        active: true,
        createdAt: new Date("2024-07-05T12:15:00Z"),
        updatedAt: new Date("2024-08-03T10:00:00Z")
    },
    {
        name: "Amortiguador delantero",
        code: "AD-003",
        description: "Amortiguador para suspensión delantera.",
        price: 75.00,
        active: true,
        createdAt: new Date("2024-08-18T09:45:00Z"),
        updatedAt: new Date("2024-09-01T13:20:00Z")
    },
    {
        name: "Bujías",
        code: "BJ-004",
        description: "Juego de bujías para encendido.",
        price: 15.20,
        active: true,
        createdAt: new Date("2024-06-25T15:30:00Z"),
        updatedAt: new Date("2024-07-15T08:10:00Z")
    },
    {
        name: "Filtro de aire",
        code: "FA-005",
        description: "Filtro para entrada de aire del motor.",
        price: 12.30,
        active: true,
        createdAt: new Date("2024-09-03T17:00:00Z"),
        updatedAt: new Date("2024-09-18T11:45:00Z")
    },
    {
        name: "Correa de distribución",
        code: "CD-006",
        description: "Correa sincronizadora del motor.",
        price: 45.99,
        active: true,
        createdAt: new Date("2024-10-01T10:20:00Z"),
        updatedAt: new Date("2024-11-10T10:00:00Z")
    },
    {
        name: "Radiador",
        code: "RD-007",
        description: "Radiador para sistema de refrigeración.",
        price: 110.00,
        active: true,
        createdAt: new Date("2024-11-12T09:00:00Z"),
        updatedAt: new Date("2024-12-01T10:00:00Z")
    },
    {
        name: "Batería 12V",
        code: "BT-008",
        description: "Batería estándar de 12 voltios.",
        price: 89.95,
        active: true,
        createdAt: new Date("2024-12-20T14:00:00Z"),
        updatedAt: new Date("2025-01-05T09:45:00Z")
    },
    {
        name: "Alternador",
        code: "AL-009",
        description: "Alternador para sistema eléctrico.",
        price: 130.00,
        active: true,
        createdAt: new Date("2025-01-15T10:15:00Z"),
        updatedAt: new Date("2025-01-30T13:00:00Z")
    },
    {
        name: "Sensor de oxígeno",
        code: "SO-010",
        description: "Sensor para mezcla aire-combustible.",
        price: 40.00,
        active: true,
        createdAt: new Date("2024-07-12T08:00:00Z"),
        updatedAt: new Date("2024-07-25T09:30:00Z")
    },
    {
        name: "Faro delantero",
        code: "FD-011",
        description: "Faro halógeno delantero derecho.",
        price: 60.00,
        active: true,
        createdAt: new Date("2024-09-20T12:30:00Z"),
        updatedAt: new Date("2024-10-01T14:00:00Z")
    },
    {
        name: "Espejo retrovisor",
        code: "ER-012",
        description: "Espejo lateral izquierdo manual.",
        price: 35.00,
        active: true,
        createdAt: new Date("2024-11-01T09:10:00Z"),
        updatedAt: new Date("2024-11-12T16:45:00Z")
    },
    {
        name: "Filtro de combustible",
        code: "FC-013",
        description: "Filtro para línea de combustible.",
        price: 18.00,
        active: true,
        createdAt: new Date("2024-08-07T11:00:00Z"),
        updatedAt: new Date("2024-08-20T10:00:00Z")
    },
    {
        name: "Compresor de aire acondicionado",
        code: "CA-014",
        description: "Compresor para sistema de A/C.",
        price: 210.00,
        active: true,
        createdAt: new Date("2025-02-01T10:00:00Z"),
        updatedAt: new Date("2025-02-15T11:00:00Z")
    },
    {
        name: "Kit de embrague",
        code: "KE-015",
        description: "Kit completo para embrague manual.",
        price: 150.00,
        active: true,
        createdAt: new Date("2024-10-18T12:00:00Z"),
        updatedAt: new Date("2024-10-28T13:30:00Z")
    },
    {
        name: "Parabrisas",
        code: "PB-016",
        description: "Cristal delantero laminado.",
        price: 95.00,
        active: true,
        createdAt: new Date("2025-03-01T14:15:00Z"),
        updatedAt: new Date("2025-03-12T10:00:00Z")
    },
    {
        name: "Tubo de escape",
        code: "TE-017",
        description: "Tubo final del sistema de escape.",
        price: 80.00,
        active: true,
        createdAt: new Date("2024-06-15T09:30:00Z"),
        updatedAt: new Date("2024-06-25T10:45:00Z")
    },
    {
        name: "Sensor de temperatura",
        code: "ST-018",
        description: "Sensor para temperatura del refrigerante.",
        price: 22.00,
        active: true,
        createdAt: new Date("2025-04-01T08:00:00Z"),
        updatedAt: new Date("2025-04-15T10:00:00Z")
    },
    {
        name: "Motor de arranque",
        code: "MA-019",
        description: "Motor eléctrico para arranque del vehículo.",
        price: 140.00,
        active: true,
        createdAt: new Date("2024-12-05T11:30:00Z"),
        updatedAt: new Date("2024-12-15T14:00:00Z")
    },
    {
        name: "Lámparas LED",
        code: "LL-020",
        description: "Juego de lámparas LED para faros.",
        price: 35.99,
        active: true,
        createdAt: new Date("2025-04-20T10:10:00Z"),
        updatedAt: new Date("2025-04-25T10:00:00Z")
    }
]);