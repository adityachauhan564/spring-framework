// The shape of the JSON that the backend sends. Its keys come from Product.java's getters (getName() -> "name").
// id is optional, because a product that is not saved yet has no id.
export interface Product {
  id?: number;
  name: string;
  price: number;
  quantity: number;
}
