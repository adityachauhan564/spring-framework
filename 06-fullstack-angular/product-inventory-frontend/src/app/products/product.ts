// The shape of the JSON the backend sends: its keys come from Product.java's getters (getName() -> "name").
// id is optional because a product that isn't saved yet has none.
export interface Product {
  id?: number;
  name: string;
  price: number;
  quantity: number;
}
