export interface Libro {
    id?:         number;
    Year?:       number;
    Title?:      string;
    handle?:     string;
    Publisher?:  string;
    ISBN?:       string;
    Pages?:      number;
    Notes?:      string[];
    created_at?: string;
    villains?:   Villain[];
}

export interface Villain {
    name?: string;
    url?:  string;
}
