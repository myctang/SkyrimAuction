import {GET_BUY_LIST} from "../constants/actionsType";


const initialState = {
    buyList: []
};

export default function getBuyList(state = initialState, action){
    console.log("reducer start");
    switch (action.type){
        case GET_BUY_LIST:
            console.log("reducer");
            return {...state, buyList: action.data};

        default:
            return state;
    }
}