#include <iostream>

using namespace std;

int main()
{
    int n = 0;
    cin >> n;
    int nums[n];
    int sum;
    for (int i = 0; i < n; i++)
    {
        cin >> nums[i];
    }
    int max = nums[0];
    for (int i = 0; i < n; i++)
    {
        sum = nums[i];
        if (sum > max)
            max = sum;
        for (int j = i + 1; j < n; j++)
        {
            sum += nums[j];
            if (sum > max)
            {
                max = sum;
            }
        }
    }
    cout << max;
    return 0;
}
