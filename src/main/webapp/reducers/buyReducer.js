import {GET_BUY_LIST, GET_MY_BIDDS} from "../constants/actionsType";


const initialState = {
    buyList: []
};

export default function getBuyList(state = initialState, action){
    console.log("reducer start");
    switch (action.type){
        case GET_BUY_LIST:
            console.log("reducer");
            return {...state, buyList: action.data};
        case GET_MY_BIDDS:
            console.log("getMyBidds reducer");
            return {...state, myBidds: action.data}
        default:
            return state;
    }
}