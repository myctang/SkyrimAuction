import {GET_MY_SELL_ITEMS} from "../constants/actionsType";

const initialState = {
    mySellItems: []
};

export default function getMySellItems(state = initialState, action){
    switch (action.type) {
        case GET_MY_SELL_ITEMS:
            return {...state, mySellItems: action.data};
        default:
            return state;
    }
}