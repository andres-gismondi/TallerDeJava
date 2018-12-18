export class User{
    id: number;
    email: string;
    token: string;
}

export class Creator{
    id: number;
    email: string;
    firstName: string;
    lastName: string;
    type: string;
    password: string;
    categories: Category[];
}

export class Category{
    id: number;
    name: string;
    writePermission: boolean;
}

export class Billboard{
    id: number;
    categories: Category[];
    title: string;
    description: string;
    date: Date;
    creator: Creator;
}

export class BillboardUser{
    billboard: Billboard;
    user: User;
}
