import React, {useState, useEffect} from 'react';
import {
  View,
  Text,
  StyleSheet,
  StatusBar,
  FlatList,
  TouchableOpacity,
  Image,
  Dimensions,
  ActivityIndicator,
} from 'react-native';
import {useTheme} from '@react-navigation/native';
import DELETE_ICON from '../assets/delete.png';
import WEBSITE_ICON from '../assets/website.png';
import AxiosAPI from './../AxiosAPI';

var width = Dimensions.get('window').width * 0.9;

const HomeScreen = ({navigation}) => {
  const {colors} = useTheme();

  const theme = useTheme();

  const [passList, setPassList] = useState([]);

  useEffect(() => {
    const onSuccess = response => {
      setPassList(response.data);
    };

    const onFailure = error => {
      console.log('Login failed');
    };

    AxiosAPI.get('/all/')
      .then(onSuccess)
      .catch(onFailure);
  }, []);

  const onDeleteHandle = item => {
    const copiedList = [...passList];

    const onSuccess = response => {
      console.log('deleted');
      setPassList(
        copiedList.filter(it => {
          return it.id != item.id;
        }),
      );
    };

    const onFailure = error => {
      console.log('delete failed');
    };

    AxiosAPI.delete('/delete/'+item.id)
      .then(onSuccess)
      .catch(onFailure);
  };

  const loading =
    passList.length == 0 ? (
      <View style={styles.loading}>
        <ActivityIndicator />
      </View>
    ) : null;

  const renderItem = ({item}) => (
    <View style={styles.listItem}>
      <Image
        source={WEBSITE_ICON}
        style={{width: 60, height: 60, borderRadius: 30}}
      />
      <View style={{alignItems: 'center', flex: 1}}>
        <Text style={{fontWeight: 'bold'}}>{item.username}</Text>
        <Text>{item.siteName}</Text>
      </View>
      <TouchableOpacity
        style={{
          height: 50,
          width: 50,
          justifyContent: 'center',
          alignItems: 'center',
        }}
        onPress={() => onDeleteHandle(item)}>
        <Image
          source={DELETE_ICON}
          style={{width: 60, height: 60, borderRadius: 30}}
        />
      </TouchableOpacity>
    </View>
  );
  return (
    <View style={styles.container}>
      {loading}
      <StatusBar barStyle={theme.dark ? 'light-content' : 'dark-content'} />
      <Text style={{color: colors.text}}>Home Screen</Text>
      <FlatList key={item => item.id} data={passList} renderItem={renderItem} />
    </View>
  );
};

export default HomeScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#897867',
    alignItems: 'center',
    justifyContent: 'center',
  },
  listItem: {
    margin: 10,
    padding: 10,
    backgroundColor: '#FFF',
    flex: 1,
    width: width,
    alignSelf: 'center',
    flexDirection: 'row',
    borderRadius: 5,
  },
});
