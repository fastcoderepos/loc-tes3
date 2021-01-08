export interface IFilm {  
	description?: string;
	filmId: number;
	lastUpdate?: Date;
	length?: number;
	rating?: string;
	rentalDuration: number;
	rentalRate: number;
	replacementCost: number;
	title: string;

	languageDescriptiveField?: number;
	languageId: number;
}
