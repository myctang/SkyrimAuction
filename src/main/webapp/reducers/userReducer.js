import {GET_USER_INFO} from "../constants/actionsType";

const initialState = {
    user:{}
};

export default function userInfo(state = initialState, action){
    switch(action.type) {
        case GET_USER_INFO:
            return {...state, user: action.data};
        default:
            return state;
    }
}