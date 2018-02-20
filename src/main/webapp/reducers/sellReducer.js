import {GET_INVENTORY} from "../constants/actionsType";

const initialState = {
    inventory: []
};

export default function getInventory(state = initialState, action){
    switch (action.type) {
        case GET_INVENTORY:
            return {...state, inventory: action.data};
        default:
            return state;
    }
}