import {GET_BUY_LIST, GET_MY_BIDDS} from "../constants/actionsType";


const initialState = {
    buyList: [],
    myBidds: []
};

export default function getBuyList(state = initialState, action){
    console.log("getBuyList->reducer start");
    switch (action.type){
        case GET_BUY_LIST:
            console.log("getBuyList->reducer->GET_BUY_LIST");
            return {...state, buyList: action.data};
        case GET_MY_BIDDS:
            console.log("getMyBidds->reducer->GET_MY_BIDDS");
            return {...state, myBidds: action.data}
        default:
            return state;
    }
}